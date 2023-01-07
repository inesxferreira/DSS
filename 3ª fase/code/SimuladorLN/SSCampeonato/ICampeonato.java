package SimuladorLN.SSCampeonato;

public interface ICampeonato {

	/**
	 * 
	 * @param idCampeonato
	 */
	void infoCampeonato(String idCampeonato);

	void listarCampeonatos();

	/**
	 * 
	 * @param idCampeonato
	 */
	Campeonato escolherCampeonato(String idCampeonato);

	/**
	 * 
	 * @param campeonato
	 */
	void simularCampeonato(String idCampeonato);

	/**
	 * 
	 * @param scoreCamp
	 */
	public void atualizaScoreCampeonato(String idCampeonato);

}