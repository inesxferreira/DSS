package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import java.util.Map;

public interface ICorrida {
	Circuito getCircuito(String idCircuito);

	void putCircuito(Circuito c);

	static void simularCorrida(Map<String, Participante> participantes, Corrida c){};
}