package SimuladorLN;

import SimuladorLN.SSCampeonato.SSCarro.ICarro;
import SimuladorLN.SSCampeonato.SSCorrida.ICorrida;
import SimuladorLN.SSCampeonato.ICampeonato;
import SimuladorLN.SSConta.IConta;

public interface ISimuladorLN {

    ICarro getCarroFacade();

    ICampeonato getCampeonatoFacade();

    ICorrida getCorridaFacade();

    IConta getContaFacade();

}