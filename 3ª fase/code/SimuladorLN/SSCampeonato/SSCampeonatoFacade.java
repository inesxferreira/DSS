package SimuladorLN.SSCampeonato;

import java.util.Map;
import java.util.TreeMap;
import BaseDeDados.CampeonatoDAO;

public class SSCampeonatoFacade implements ICampeonato {
	/* campeonatos existentes */

	//TreeMap<String, Integer> scoreCamp = new TreeMap<String, Integer>();
	// private CampeonatoDAO todos_campeonatos;
	private Map<String, Campeonato> todos_campeonatos;

	/**
	 * Construtor por omissão para objetos da classe SSCarroFacade.
	 */
	public SSCampeonatoFacade() {
		this.todos_campeonatos = CampeonatoDAO.getInstance();
	}

	/**
	 * Método que devolve um campeonato dado o seu identificador.
	 *
	 * @param idCamp id do campeonato.
	 * @return Campeonato.
	 */
	public Campeonato getCampeonato(String idCamp) {
		return this.todos_campeonatos.get(idCamp);
	}

	/**
	 * Método que permite adicionar um carro.
	 *
	 * @param c Carro a adicionar.
	 */
	public void putCampeonato(Campeonato c) {
		this.todos_campeonatos.put(c.getIdCampeonato(), c.clone());
	}

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

	@Override
	public void setScoreCampeonato(TreeMap<String, Integer> scoreCamp) {
		// TODO Auto-generated method stub

	}

}
