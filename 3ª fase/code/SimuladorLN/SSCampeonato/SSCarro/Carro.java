package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public abstract class Carro {
	private int idCarro;
	private String modelo;
	private String marca;
	private String categoria;
	private Float pac;
	private double fiabilidade;
	private int tipoPneus;
	private int modoMotor;
	private int potenciaC;
	private int cilindrada;

	/* Construtores */
	public Carro() {
		this.idCarro = 0;
		this.modelo = "";
		this.marca = "";
		this.categoria = "";
		this.pac = 0.0f;
		this.fiabilidade = 0.0f;
		this.tipoPneus = 0;
		this.modoMotor = 0;
		this.potenciaC = 0;
		this.cilindrada = 0;
	}

	public Carro(Integer idCarro, String marca, String modelo, String categoria, int potenciaC,
			int cilindrada) {
		this.idCarro = idCarro;
		this.marca = marca;
		this.modelo = modelo;
		this.categoria = categoria;
		this.potenciaC = potenciaC;
		this.cilindrada = cilindrada;
	}

	public Carro(Carro c) {
		this.idCarro = c.getIdCarro();
		this.marca = c.getMarca();
		this.modelo = c.getModelo();
		this.categoria = c.getCategoria();
		this.pac = c.getPac();
		this.fiabilidade = c.getFiabilidade();
		this.tipoPneus = c.getTipoPneus();
		this.modoMotor = c.getModoMotor();
		this.potenciaC = c.getPotenciaC();
		this.cilindrada = c.getCilindrada();
	}

	/* Getters */
	public Integer getIdCarro() {
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

	public double getFiabilidade() {
		return this.fiabilidade;
	}

	public int getTipoPneus() {
		return this.tipoPneus;
	}

	public int getModoMotor() {
		return this.modoMotor;
	}

	public int getPotenciaC() {
		return this.potenciaC;
	}

	public int getCilindrada() {
		return this.cilindrada;
	}

	/* Setters */
	public void setIdCarro(Integer idCarro) {
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

	public void setPac(float pac) {
		this.pac = pac;
	}

	public void setFiabilidade(double fiabilidade) {
		this.fiabilidade = fiabilidade;
	}

	public void setPneus(int tipoPneus) {
		this.tipoPneus = tipoPneus;
	}

	public void setModoMotor(int modoMotor) {
		this.modoMotor = modoMotor;
	}

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
		return ((this.idCarro == c.getIdCarro()) &&
				this.modelo.equals(c.getModelo()) &&
				this.marca.equals(c.getMarca()) &&
				this.categoria.equals(c.getCategoria()) &&
				this.pac == c.getPac() &&
				this.fiabilidade == c.getFiabilidade() &&
				this.tipoPneus == c.getTipoPneus() &&
				this.modoMotor == c.getModoMotor() &&
				this.potenciaC == c.getPotenciaC() &&
				this.cilindrada == c.getCilindrada());
	}

	public abstract Carro clone();

	/**
	 * Método que calcula e atribui uma fiabilida a um carro.
	 *
	 * @param idCarro  identificador do carro.
	 * @param n_voltas número de voltas da corrida.
	 * @param cts      habilidade chuva vs tempo seco do piloto.
	 * @param sva      agressividade do piloto.
	 */
	public abstract void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva);

}