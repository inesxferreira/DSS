package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public abstract class Carro {
	private String idCarro;
	private String modelo;
	private String marca;
	private String categoria;
	private Float pac;
	private Float fiabilidade;
	private int tipoPneus;
	private int modoMotor;
	private int potencia;
	private int potenciaC;
	private int cilindrada;

	/* Construtores */
	public Carro() {
		this.idCarro = "";
		this.modelo = "";
		this.marca = "";
		this.categoria = "";
		this.pac = 0.0f;
		this.fiabilidade = 0.0f;
		this.tipoPneus = 0;
		this.modoMotor = 0;
		this.potencia = 0;
		this.potenciaC = 0;
		this.cilindrada = 0;
	}

	public Carro(String idCarro, String modelo, String marca, String categoria, Float pac, Float fiabilidade,
			int tipoPneus, int modoMotor,
			int potencia, int potenciaC, int cilindrada) {
		this.idCarro = idCarro;
		this.modelo = modelo;
		this.marca = marca;
		this.categoria = categoria;
		this.pac = pac;
		this.fiabilidade = fiabilidade;
		this.tipoPneus = tipoPneus;
		this.modoMotor = modoMotor;
		this.potencia = potencia;
		this.potenciaC = potenciaC;
		this.cilindrada = cilindrada;
	}

	public Carro(Carro c) {
		this.idCarro = c.getIdCarro();
		this.modelo = c.getModelo();
		this.marca = c.getMarca();
		this.categoria = c.getCategoria();
		this.pac = c.getPac();
		this.fiabilidade = c.getFiabilidade();
		this.tipoPneus = c.getTipoPneus();
		this.modoMotor = c.getModoMotor();
		this.potencia = c.getPotencia();
		this.potenciaC = c.getPotenciaC();
		this.cilindrada = c.getCilindrada();
	}

	/* Getters */
	public String getIdCarro() {
		return this.idCarro;
	}

	public String getModelo() {
		return this.modelo;
	}

	public String getMarca() {
		return this.marca;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public Float getPac() {
		return this.pac;
	}

	public Float getFiabilidade() {
		return this.fiabilidade;
	}

	public int getTipoPneus() {
		return this.tipoPneus;
	}

	public int getModoMotor() {
		return this.modoMotor;
	}

	public int getPotencia() {
		return this.potencia;
	}

	public int getPotenciaC() {
		return this.potenciaC;
	}

	public int getCilindrada() {
		return this.cilindrada;
	}

	/* Setters */
	public void setIdCarro(String idCarro) {
		this.idCarro = idCarro;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setPac(Float pac) {
		this.pac = pac;
	}

	public void setFiabilidade(Float fiabilidade) {
		this.fiabilidade = fiabilidade;
	}

	/**
	 * 
	 * @param tipoPneus
	 */
	public void setPneus(int tipoPneus) {
		this.tipoPneus = tipoPneus;
	}

	public void setModoMotor(int modoMotor) {
		this.modoMotor = modoMotor;
	}

	/**
	 * 
	 * @param potenciaC
	 * @param potenciaE
	 */
	public void setPotencia(int potenciaC, int potenciaE) {
		this.potencia = potenciaC + potenciaE;
	}

	/**
	 * 
	 * @param pac
	 */
	public void setPotenciaC(int potenciaC) {
		this.potenciaC = potenciaC;
	}

	/**
	 * 
	 * @param cilindrada
	 */
	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double calculaFiabilidade(int n_voltas, int cts, int sva) {
		double fiabilidade;
		switch (this.categoria) {
			case "C1":
				fiabilidade = 0.95;
				break;
			case "C2":
				fiabilidade = 0.8 * (1 - 0.2 * (5000 - this.getCilindrada()) / 2000);
				break;
			case "GT":
				fiabilidade = 0.7 * Math.pow(0.99, n_voltas) * (1 - 0.2 * (4000 - this.getCilindrada()) / 2000);
				break;
			case "SC":
				fiabilidade = 0.75 * (1 - 0.2 * (2500 - this.getCilindrada()) / 500)
						* (1 - 0.2 * (100 - (cts + sva)) / 100);
				break;
			default:
				fiabilidade = 0;
				break;
		}
		return fiabilidade;
	}

	/* Metodos usuais */

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nId: ");
		sb.append(this.idCarro);
		sb.append("\nMarca: ");
		sb.append(this.marca);
		sb.append("\nModelo: ");
		sb.append(this.modelo);
		sb.append("\nCategoria: ");
		sb.append(this.categoria);
		sb.append("\nPAC: ");
		sb.append(this.pac);
		sb.append("\nFiabilidade: ");
		sb.append(this.fiabilidade);
		sb.append("\nTipo de Pneus: ");
		sb.append(this.tipoPneus);
		sb.append("\nModo do Motor: ");
		sb.append(this.modoMotor);
		sb.append("\nPotencia: ");
		sb.append(this.potencia);
		sb.append("\nCilindrada: ");
		sb.append(this.cilindrada);
		return sb.toString();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || this.getClass() != o.getClass())
			return false;

		Carro c = (Carro) o;
		return (this.idCarro.equals(c.getIdCarro()) &&
				this.modelo.equals(c.getModelo()) &&
				this.marca.equals(c.getMarca()) &&
				this.categoria.equals(c.getCategoria()) &&
				this.pac == c.getPac() &&
				this.fiabilidade == c.getFiabilidade() &&
				this.tipoPneus == c.getTipoPneus() &&
				this.modoMotor == c.getModoMotor() &&
				this.potencia == c.getPotencia() &&
				this.potenciaC == c.getPotenciaC() &&
				this.cilindrada == c.getCilindrada());
	}

	public abstract Carro clone();

}
