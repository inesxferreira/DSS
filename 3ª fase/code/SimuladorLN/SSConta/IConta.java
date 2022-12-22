package SimuladorLN.SSConta;

public interface IConta {

	Int getScore();

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	void verificaAfinacao(Int nCorridas, Int nAfinacoes);

	/**
	 * 
	 * @param idParticipante
	 */
	Int getVersao(String idParticipante);

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	void fazerLogin(String nome, String password);

	/**
	 * 
	 * @param c
	 */
	void setCarro(Carro c);

	Carro getCarro();

	/**
	 * 
	 * @param p
	 */
	void setPiloto(Piloto p);

	Piloto getPiloto();

	void configuraCampeonato();

}