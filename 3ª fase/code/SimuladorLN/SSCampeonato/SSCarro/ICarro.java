package SimuladorLN.SSCampeonato.SSCarro;

public interface ICarro {

	void fiabilidade();

	/**
	 * 
	 * @param tipoPneus
	 */
	void setPneus(int tipoPneus);

	/**
	 * 
	 * @param valor
	 */
	void setDownforce(Float valor);

	/**
	 * 
	 * @param modo
	 */
	void setMotor(int modo);

	void setHibrido();

}