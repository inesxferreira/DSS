package SimuladorLN.SSCampeonato.SSCarro;

public abstract class Carro {

	private Float pac;
	private String modelo;
	private String marca;
	private String idCarro;
	private String categoria;
	private Int tipoPneus;
	private Int modoMotor;
	private Int potenciaC;
	private Int cilindrada;
	private Int potencia;

	/**
	 * 
	 * @param pac
	 */
	public void setPac(Float pac) {
		this.pac = pac;
	}

	/**
	 * 
	 * @param cilindrada
	 */
	public void setCilindrada(Int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public abstract void fiabilidade();

	/**
	 * 
	 * @param tipoPneus
	 */
	public void setPneus(Int tipoPneus) {
		// TODO - implement Carro.setPneus
		throw new UnsupportedOperationException();
	}

	public Int getPotencia() {
		return this.potencia;
	}

	/**
	 * 
	 * @param potenciaC
	 * @param potenciaE
	 */
	public void setPotencia(Int potenciaC, Int potenciaE) {
		// TODO - implement Carro.setPotencia
		throw new UnsupportedOperationException();
	}

}