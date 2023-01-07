package SimuladorLN.SSCampeonato.SSCorrida;

import SimuladorLN.SSConta.Participante;
import java.util.Map;

public interface ICorrida {

	void simularCorrida(Map<String, Participante> participantes, Corrida corrida);
}