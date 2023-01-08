package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;
import SimuladorLN.SSConta.Participante;
import java.lang.Math;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;

public class Corrida {

	private Integer idCorrida;
	private int voltas;
	private int meteorologia;
	private Circuito circuito;
	private TreeMap<String, Integer> scoreCorrida; // IdParticipante, ScoreCorrida

	private TreeMap<Participante, Double> fatores_ultrapassagem = new TreeMap<>(new Comparator<Participante>() {
		public int compare(Participante s1, Participante s2) {
			Double p1 = fatores_ultrapassagem.get(s1);
			Double p2 = fatores_ultrapassagem.get(s2);
			return p2.compareTo(p1);
		}
	});

	private TreeMap<Participante, Integer> quant_despistes = new TreeMap<>(new Comparator<Participante>() {
		public int compare(Participante s1, Participante s2) {
			Integer p1 = quant_despistes.get(s1);
			Integer p2 = quant_despistes.get(s2);
			return p2.compareTo(p1);
		}
	});

	/* Construtores */
	public Corrida() {
		this.idCorrida = 0;
		this.voltas = 0;
		this.meteorologia = 0;
		this.circuito = null;
		this.scoreCorrida = new TreeMap<>();
	}

	public Corrida(Integer idCorrida, int voltas, int meteorologia, Circuito circuito, TreeMap<String, Integer> score) {
		this.idCorrida = idCorrida;
		this.voltas = voltas;
		this.meteorologia = meteorologia;
		this.circuito = circuito.clone();
		this.scoreCorrida = score;
	}

	public Corrida(Corrida c) {
		this.idCorrida = c.getIdCorrida();
		this.meteorologia = c.getMeteorologia();
		this.voltas = c.getVoltas();
		this.circuito = c.getCircuito();
		this.scoreCorrida = c.getScoreCorrida();
	}

	public Circuito getCircuito() {
		return this.circuito.clone();
	}

	// fui eu que fizio n sei se é util
	public Integer getScoreCorrida(String idParticipante) {
		return this.scoreCorrida.get(idParticipante);
	}

	public TreeMap<String, Integer> getScoreCorrida() {
		return this.scoreCorrida;
	}

	public Integer getIdCorrida() {
		return this.idCorrida;
	}

	public int getVoltas() {
		return this.voltas;
	}

	public int getMeteorologia() {
		return this.meteorologia;
	}

	/**
	 * 
	 * @param circuito
	 */
	public void setCircuito(Circuito circuito) {
		this.circuito = circuito;
	}

	/**
	 * 
	 * @param voltas
	 */
	public void setIdCorrida(Integer idCorrida) {
		this.idCorrida = idCorrida;
	}

	/**
	 * 
	 * @param voltas
	 */
	public void setScoreCorrida(TreeMap<String, Integer> score) {
		this.scoreCorrida = score;
	}

	public void atribuiScore() {
		Integer n = (Integer) this.fatores_ultrapassagem.size();
		for (Participante p : fatores_ultrapassagem.keySet()) {
			if (n > 2) {
				this.scoreCorrida.put(p.getIdParticipante(), n);
			} else if (n == 2) {
				this.scoreCorrida.put(p.getIdParticipante(), n);
			} else if (n == 1) {
				this.scoreCorrida.put(p.getIdParticipante(), 1);
			} else
				this.scoreCorrida.put(p.getIdParticipante(), 0);
			n--;
		}
	}

	/**
	 * 
	 * @param voltas
	 */
	public void setVoltas(int voltas) {
		this.voltas = voltas;
	}

	/**
	 * 
	 * @param meteoeologia
	 */
	public void setMeteorologia(int meteorologia) {
		this.meteorologia = meteorologia;
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularUltrapassagem(Participante participante, int gdu) {
		Carro c = participante.getCarro();
		int potencia = c.getPotenciaC();
		float pac = c.getPac();
		int tipoPneus = c.getTipoPneus();
		int modoMotor = c.getModoMotor();

		Piloto p = participante.getPiloto();
		float sva = p.getSVA();
		float cts = p.getCTS();

		double fator_ultrapassagem = 0.1 * gdu + 0.8 * (potencia + 1 - pac + tipoPneus + modoMotor) + 0.1 * (sva + cts);
		this.fatores_ultrapassagem.put(participante, fator_ultrapassagem);
	}

	/**
	 * 
	 * @param participantes
	 */
	// public void calcularDespistes(Map<String, Participante> participantes) {
	public void calcularDespistes(Participante p) {
		Random random = new Random();
		int despistes = random.nextInt(3) + random.nextInt(3);
		if (despistes > 5)
			despistes = random.nextInt(2);
		this.quant_despistes.put(p, despistes);
		fatores_ultrapassagem.put(p, fatores_ultrapassagem.get(p) * (Math.pow(0.8, despistes)));
		// fatores_ultrapassagem -> passa a ser o valor * 0.8 elevado ao numero de
		// despistes
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularAvarias(Map<String, Participante> participantes) {
		for (Participante p : participantes.values()) {
			Carro c = p.getCarro();
			if (c.getFiabilidade() < 0.32)
				desclassifica(p.getIdParticipante());
		}
	}

	/**
	 * 
	 * @param participantes
	 */
	public Map<String, Participante> calcularEventosBase(Map<String, Participante> participantes) {
		List<Caracteristica> caracteristicas = this.circuito.getCaracteristicas();

		for (Caracteristica c : caracteristicas) {
			int gdu = c.getGDU();
			for (Participante participante : participantes.values()) {
				calcularUltrapassagem(participante, gdu);
			}
		}
		return participantes;
	}

	/**
	 * 
	 * @param participantes
	 */
	public Map<String, Participante> calcularEventosPremium(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosPremium
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idParticipante ID do participante
	 * @param corrida        Corrida em que o participante é desclassificado
	 */
	public void desclassifica(String idParticipante) {
		this.getScoreCorrida().put(idParticipante, 0);
	}

	public Corrida clone() {
		return new Corrida(this);
	}

}