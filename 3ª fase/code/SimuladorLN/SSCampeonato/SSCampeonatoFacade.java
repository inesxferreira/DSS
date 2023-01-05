package SimuladorLN.SSCampeonato;

import java.util.TreeMap;

import BaseDeDados.*;

public class SSCampeonatoFacade implements ICampeonato {
	/* campeonatos existentes */

	TreeMap<String, Integer> scoreCamp = new TreeMap<String, Integer>();
	private CampeonatoDAO todos_campeonatos;

	/**
	 * 
	 * @param idCampeonato
	 */

	public String infoCampeonato(String idCampeonato) {
		throw new UnsupportedOperationException();
	}

	/* Constutor por omissão */
	public SSCampeonatoFacade(String todos_campeonatos) {
		this.todos_campeonatos = CampeonatoDAO.getInstance();
	}

	// dar print de listar campeaonato
	public void listarCampeonatos() {

		// TODO - implement SSCampeonatoFacade.listarCampeonatos
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idCampeonato
	 */
	public Campeonato escolherCampeonato(String idCampeonato) {
		// TODO - implement SSCampeonatoFacade.escolherCampeonato
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param campeonato
	 */
	public void simularCampeonato(Campeonato campeonato) {
		// TODO - implement SSCampeonatoFacade.simularCampeonato
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param scoreCamp
	 */
	public void setScoreCampeonato(TreeMap<String, Integer> scoreCamp) {

	}

}
