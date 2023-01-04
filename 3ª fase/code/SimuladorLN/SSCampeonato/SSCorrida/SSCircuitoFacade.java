package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import BaseDeDados.CircuitoDAO;
import java.util.*;

public class SSCircuitoFacade implements ICircuito {

	private Map<String, Circuito> todos_circuitos;

	/**
	 * Construtor por omissão para objetos da classe SSCarroFacade.
	 */
	public SSCircuitoFacade() {
		this.todos_circuitos = CircuitoDAO.getInstance();
	}

	public void simularCorrida() {
		// TODO - implement SSCorridaFacade.simularCorrida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public void setScoreCorrida(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.setScoreCorrida
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public Map<String, Participante> calcularEventosBase(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosBase
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participantes
	 */
	public Map<String, Participante> calcularEventosPremium(Map<String, Participante> participantes) {
		// TODO - implement SSCorridaFacade.calcularEventosPremium
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idParticipante
	 */
	public void desclassifica(String idParticipante) {
		// TODO - implement SSCorridaFacade.desclassifica
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param participante
	 */
	public void registaParticipante(Participante participante) {
		// TODO - implement SSCorridaFacade.registaParticipante
		throw new UnsupportedOperationException();
	}

}