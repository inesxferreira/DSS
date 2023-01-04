package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import java.util.List;
import java.util.Map;

public interface ICircuito {

	void simularCorrida();

	/**
	 * 
	 * @param participantes
	 */
	void setScoreCorrida(Map<String, Participante> participantes);

	/**
	 * 
	 * @param participantes
	 */
	Map<String, Participante> calcularEventosBase(Map<String, Participante> participantes);

	/**
	 * 
	 * @param participantes
	 */
	Map<String, Participante> calcularEventosPremium(Map<String, Participante> participantes);

	/**
	 * 
	 * @param idParticipante
	 */
	void desclassifica(String idParticipante);

	/**
	 * 
	 * @param participante
	 */
	void registaParticipante(Participante participante);

}