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
        this.todos_circuitos.put(c.getIdCircuito(), c);
    }

	public void simularCorrida(Map<String, Participante> participantes) {
		// TODO Auto-generated method stub
		
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
	 * @param participantes
	 */
	public void setScoreCorrida(Map<String, Participante> participantes, Corrida corrida) {
		// Atribuir um score de acordo com a ordem dos participantes
		int score = 5;
		int i = 0;

		Object [] parti = participantes.keySet().toArray();

		while(score != 0){
			String id = parti[i].toString();
			corrida.getScoreCorrida().put(id, score);
			score--;
			i++;
		}
		//participantes.keySet().forEach(id->corrida.getScoreCorrida().put(id, score));
	}

	/**
	 * 
	 * @param idParticipante ID do participante
	 * @param corrida Corrida em que o participante é desclassificado
	 */
	public void desclassifica(String idParticipante, Corrida corrida) {
		corrida.getScoreCorrida().put(idParticipante, 0);
	}

}