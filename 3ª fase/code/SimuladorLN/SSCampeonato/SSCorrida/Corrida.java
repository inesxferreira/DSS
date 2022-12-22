package SimuladorLN.SSCampeonato.SSCorrida;

public class Corrida {

	private Circuito circuito;
	private Int voltas;
	private Int meteorologia;
	private TreeSet<String, Int> scoreCorr;
	private String idCorrida;

	public Int getMeteorologia() {
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
	public void setVoltas(Int voltas) {
		this.voltas = voltas;
	}

	public Int getVoltas() {
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