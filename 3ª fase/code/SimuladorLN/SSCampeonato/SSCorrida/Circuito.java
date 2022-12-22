package SimuladorLN.SSCampeonato.SSCorrida;

public class Circuito {

	private Float distancia;
	private Int nCurvas;
	private Int nChicanes;
	private Int nRetas;
	private String nomeCircuito;

	private void calcularNRetas() {
		// TODO - implement Circuito.calcularNRetas
		throw new UnsupportedOperationException();
	}

	public Map<String, Caracteristica> getCaracteristicas() {
		// TODO - implement Circuito.getCaracteristicas
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param caracteristicas
	 */
	public void definirGDU(Map<String, Caracteristica> caracteristicas) {
		// TODO - implement Circuito.definirGDU
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param distancia
	 */
	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}

	/**
	 * 
	 * @param nCurvas
	 */
	public void setNCurvas(Int nCurvas) {
		this.nCurvas = nCurvas;
	}

	/**
	 * 
	 * @param nChicanes
	 */
	public void setNChicanes(Int nChicanes) {
		this.nChicanes = nChicanes;
	}

}