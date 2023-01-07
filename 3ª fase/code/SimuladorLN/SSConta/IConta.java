package SimuladorLN.SSConta;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public interface IConta {

	int getScore();

	int getId();

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	boolean verificaAfinacao(int nCorridas, int nAfinacoes);

	/**
	 * 
	 * @param idParticipante
	 */
	boolean getVersao(String idParticipante);

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

	boolean verificarCredenciais(String nome, String pass);

	Carro getCarro();

	/**
	 * 
	 * @param p
	 */
	void setPiloto(Piloto p);

	Piloto getPiloto();

	void configuraCampeonato();

}