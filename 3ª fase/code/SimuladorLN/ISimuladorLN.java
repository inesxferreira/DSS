package SimuladorLN;

import SimuladorLN.SSCampeonato.SSCarro.ICarro;
import SimuladorLN.SSCampeonato.SSCorrida.ICorrida;

import java.io.IOException;

import SimuladorLN.SSCampeonato.ICampeonato;
import SimuladorLN.SSConta.IConta;
import UserInterface.Menu.PreCondition;

public interface ISimuladorLN {

    ICarro getCarroFacade();

    ICampeonato getCampeonatoFacade();

    ICorrida getCorridaFacade();

    IConta getContaFacade();

    void start();

    boolean login(String user, String password) throws IOException;

    void shutdown();

    PreCondition existeConta();

}