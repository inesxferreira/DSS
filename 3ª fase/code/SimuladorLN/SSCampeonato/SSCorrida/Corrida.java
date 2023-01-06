package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import java.util.Map;
import java.util.TreeMap;

public class Corrida {

	private String idCorrida;
	private int voltas;
	private int meteorologia;
	private Circuito circuito;
	private TreeMap<String, Integer> scoreCorrida; // IdParticipante, ScoreCorrida

	/* Construtores */
	public Corrida() {
		this.idCorrida = "";
		this.voltas = 0;
		this.meteorologia = 0;
		this.circuito = null;
		this.scoreCorrida = new TreeMap<>();
	}

	public Corrida(String idCorrida, int voltas, int meteorologia, Circuito circuito, TreeMap<String, Integer> score) {
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

	public TreeMap<String, Integer> getScoreCorrida() {
		return this.scoreCorrida;
	}

	public String getIdCorrida() {
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
	public void setIdCorrida(String idCorrida) {
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
	public void calcularDespistes(Map<String, Participante> participantes) {

	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularAvarias(Map<String, Participante> participantes) {

	}
}