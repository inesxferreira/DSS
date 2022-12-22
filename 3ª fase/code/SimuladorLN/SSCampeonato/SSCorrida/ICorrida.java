package SimuladorLN.SSCampeonato.SSCorrida;

public interface ICorrida {

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
	List<Participante> calcularEventosPremium(Map<String, Participante> participantes);

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