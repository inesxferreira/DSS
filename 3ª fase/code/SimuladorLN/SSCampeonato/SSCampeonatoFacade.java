package SimuladorLN.SSCampeonato;

import java.util.Map;
import BaseDeDados.CampeonatoDAO;
import SimuladorLN.SSCampeonato.SSCorrida.Corrida;
import SimuladorLN.SSCampeonato.SSCorrida.SSCorridaFacade;

public class SSCampeonatoFacade implements ICampeonato {
	/* campeonatos existentes */

	// private CampeonatoDAO todos_campeonatos;
	private Map<String, Campeonato> todos_campeonatos;

	/* Constutor por omissão */
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
	public void infoCampeonato(String idCampeonato) {
		Campeonato camp = todos_campeonatos.get(idCampeonato);
		System.out.println("Nome: " + camp.getNome() + "\n" +
				"Circuitos:" + camp.getCircuitos().values().toString());
	}

	// Print da lista de campeaonatos
	public void listarCampeonatos() {

		for (String campId : todos_campeonatos.keySet()) {
			System.out.println("Campeonato: " + campId);
			infoCampeonato(campId);
		}

	}

	/**
	 * 
	 * @param idCampeonato
	 */
	public Campeonato escolherCampeonato(String idCampeonato) {
		Campeonato camp = todos_campeonatos.get(idCampeonato);
		return camp;
	}

	/**
	 * 
	 * @param campeonato
	 */
	public void simularCampeonato(String idCampeonato) {
		Campeonato camp = todos_campeonatos.get(idCampeonato);

		for (Corrida c : camp.getCorridas().values()) {
			SSCorridaFacade.simularCorrida(camp.getParticipantes(), c);
		}
		atualizaScoreCampeonato(idCampeonato);
	}

	@Override
	// public void setScoreCampeonato(TreeMap<String, Integer> scoreCamp) {
	public void atualizaScoreCampeonato(String idCampeonato) {
		Campeonato camp = todos_campeonatos.get(idCampeonato);

		// Para cada corrida do campeonato
		for (Corrida c : camp.getCorridas().values()) {
			// Vai buscar o score de cada participante naquela corrida
			c.getScoreCorrida().entrySet().stream()
					.forEach(idP_score -> camp.scoreCamp.put(idP_score.getKey(), idP_score.getValue()));
		}
	}
}
