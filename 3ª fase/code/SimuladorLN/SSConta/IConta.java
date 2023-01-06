package SimuladorLN.SSConta;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public interface IConta {

	int getScore();

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	void verificaAfinacao(int nCorridas, int nAfinacoes);

	/**
	 * 
	 * @param idParticipante
	 */
	int getVersao(String idParticipante);

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