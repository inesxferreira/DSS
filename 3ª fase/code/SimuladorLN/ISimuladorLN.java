package SimuladorLN;

import SimuladorLN.SSCampeonato.SSCarro.ICarro;
import SimuladorLN.SSCampeonato.SSCorrida.ICircuito;
import SimuladorLN.SSCampeonato.ICampeonato;
import SimuladorLN.SSConta.IConta;

public interface ISimuladorLN {

    ICarro getCarroFacade();

    ICampeonato getCampeonatoFacade();

    ICircuito getCircuitoFacade();

    IConta getContaFacacde();

    void start();

    // boolean login(String user, String password) throws IOException;

    void shutdown();

}