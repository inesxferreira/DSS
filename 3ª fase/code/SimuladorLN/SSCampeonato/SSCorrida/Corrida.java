package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;
import SimuladorLN.SSConta.Participante;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Corrida {

	private Integer idCorrida;
	private int voltas;
	private int meteorologia;
	private Circuito circuito;
	private TreeMap<String, Integer> scoreCorrida; // IdParticipante, ScoreCorrida

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
	public void calcularUltrapassagem(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosPremium
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularDespistes(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosPremium
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularAvarias(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosPremium
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public Map<String, Participante> calcularEventosBase(Map<String, Participante> participantes) {
		List<Caracteristica> caracteristicas = this.circuito.getCaracteristicas();

		for (Participante participante : participantes.values()) {
			Carro c = participante.getCarro();
			int potencia = c.getPotenciaC();
			float pac = c.getPac();
			int tipoPneus = c.getTipoPneus();

			Piloto p = participante.getPiloto();
			float sva = p.getSVA();
			float cts = p.getCTS();
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
	public void desclassifica(String idParticipante, Corrida corrida) {
		corrida.getScoreCorrida().put(idParticipante, 0);
	}

	public Corrida clone() {
		return new Corrida(this);
	}

}