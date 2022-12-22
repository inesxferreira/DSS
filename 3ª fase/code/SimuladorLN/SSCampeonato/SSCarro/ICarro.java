package SimuladorLN.SSCampeonato.SSCarro;

public interface ICarro {

	void fiabilidade();

	/**
	 * 
	 * @param tipoPneus
	 */
	void setPneus(Int tipoPneus);

	/**
	 * 
	 * @param valor
	 */
	void setDownforce(Float valor);

	/**
	 * 
	 * @param modo
	 */
	void setMotor(Int modo);

	void setHibrido();

}