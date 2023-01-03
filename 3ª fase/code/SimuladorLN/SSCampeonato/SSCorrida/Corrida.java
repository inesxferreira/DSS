package SimuladorLN.SSCampeonato.SSCorrida;

public class Corrida {

	private Circuito circuito;
	private int voltas;
	private int meteorologia;
	private TreeSet<String, int> scoreCorr;
	private String idCorrida;

	public int getMeteorologia() {
		return this.meteorologia;
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularDespistes(Map<String, Participante> participantes) {
		// TODO - implement Corrida.calcularDespistes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public void calcularAvarias(Map<String, Participante> participantes) {
		// TODO - implement Corrida.calcularAvarias
		throw new UnsupportedOperationException();
	}

	public Map<String, Participante> getParticipantes() {
		// TODO - implement Corrida.getParticipantes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voltas
	 */
	public void setVoltas(int voltas) {
		this.voltas = voltas;
	}

	public int getVoltas() {
		return this.voltas;
	}

	/**
	 * 
	 * @param participante
	 */
	public void registaParticipante(Participante participante) {
		// TODO - implement Corrida.registaParticipante
		throw new UnsupportedOperationException();
	}

}