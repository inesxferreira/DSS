package SimuladorLN.SSCampeonato.SSCorrida;

public class Caracteristica {

	private Integer idCaracteristica;
	private String nomeCaracteristica;
	private int gdu;

	/* Construtores */
	public Caracteristica() {
		this.idCaracteristica = 0;
		this.gdu = 0;
	}

	public Caracteristica(Integer idCaracteristica, String nome, int gdu) {
		this.idCaracteristica = idCaracteristica;
		this.nomeCaracteristica = nome;
		this.gdu = gdu;

	}

	public Caracteristica(Caracteristica c) {
		this.idCaracteristica = c.getIdCaracteristica();
		this.gdu = c.getGDU();
	}

	/* Getters */
	public Integer getIdCaracteristica() {
		return this.idCaracteristica;
	}

	public String getNomeCaracteristica() {
		return this.nomeCaracteristica;
	}

	public int getGDU() {
		return this.gdu;
	}

	/**
	 * 
	 * @param gdu
	 */
	public void setGDU(int gdu) {
		this.gdu = gdu;
	}

	public void setNomeCaracteristica(String nome) {
		this.nomeCaracteristica = nome;
	}

	/**
	 * 
	 * @param gdu
	 */
	public void setIdCaracteristica(Integer id) {
		this.idCaracteristica = id;
	}

	public Caracteristica clone() {
		return new Caracteristica(this);
	}

}