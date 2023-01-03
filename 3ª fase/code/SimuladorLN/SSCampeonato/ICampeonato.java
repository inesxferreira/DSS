package SimuladorLN.SSCampeonato;

import java.util.TreeMap;

public interface ICampeonato {

	/**
	 * 
	 * @param idCampeonato
	 */
	String infoCampeonato(String idCampeonato);

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
	void simularCampeonato(Campeonato campeonato);

	/**
	 * 
	 * @param scoreCamp
	 */
	void setScoreCampeonato(TreeMap<String, Integer> scoreCamp);

}