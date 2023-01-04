package SimuladorLN.SSCampeonato.SSCorrida;

public class Caracteristica {

	private String idCaracteristica;
	private float gdu;

	/* Construtores */
	public Caracteristica() {
		this.idCaracteristica = "";
		this.gdu = 0.0f;
	}

	public Caracteristica(String idCaracteristica, float gdu) {
		this.idCaracteristica = idCaracteristica;
		this.gdu = gdu;

	}

	public Caracteristica(Caracteristica c) {
		this.idCaracteristica = c.getIdCaracteristica();
		this.gdu = c.getGDU();
	}

	/* Getters */
	public String getIdCaracteristica() {
		return this.idCaracteristica;
	}

	public float getGDU() {
		return this.gdu;
	}

	/**
	 * 
	 * @param gdu
	 */
	public void setGDU(float gdu) {
		this.gdu = gdu;
	}

	public Caracteristica clone(){
		return new Caracteristica(this);
	}

}