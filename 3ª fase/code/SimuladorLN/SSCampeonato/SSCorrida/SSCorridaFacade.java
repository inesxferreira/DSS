package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import BaseDeDados.CircuitoDAO;
import java.util.*;

public class SSCorridaFacade implements ICorrida {

	private Map<String, Circuito> todos_circuitos;

	/**
	 * Construtor por omissão para objetos da classe SSCarroFacade.
	 */
	public SSCorridaFacade() {
		this.todos_circuitos = CircuitoDAO.getInstance();
	}

	/**
	 * Método que devolve um carro dado o seu identificador.
	 *
	 * @param idCircuito id do circuito.
	 * @return Circuito.
	 */
	public Circuito getCircuito(String idCircuito) {
		return this.todos_circuitos.get(idCircuito);
	}

	/**
	 * Método que permite adicionar um carro.
	 *
	 * @param c Carro a adicionar.
	 */
	public void putCircuito(Circuito c) {
		this.todos_circuitos.put(c.getIdCircuito().toString(), c.clone());
	}

	public static void simularCorrida(Map<String, Participante> participantes, Corrida c) {
		c.calcularEventosBase(participantes);
	}

	/**
	 * 
	 * @param participantes
	 */
	/*
	 * public void setScoreCorrida(Map<String, Participante> participantes, Corrida
	 * corrida) {
	 * // Atribuir um score de acordo com a ordem dos participantes
	 * int score = 5;
	 * int i = 0;
	 * 
	 * Object[] parti = participantes.keySet().toArray();
	 * 
	 * while (score != 0) {
	 * String id = parti[i].toString();
	 * corrida.getScoreCorrida().put(id, score);
	 * score--;
	 * i++;
	 * }
	 * // participantes.keySet().forEach(id->corrida.getScoreCorrida().put(id,
	 * score));
	 * }
	 */

}