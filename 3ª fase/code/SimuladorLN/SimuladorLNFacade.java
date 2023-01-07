package SimuladorLN;

import SimuladorLN.SSCampeonato.SSCarro.ICarro;
import SimuladorLN.SSCampeonato.SSCarro.SSCarroFacade;
import SimuladorLN.SSCampeonato.SSCorrida.ICorrida;
import SimuladorLN.SSCampeonato.SSCorrida.SSCorridaFacade;
import SimuladorLN.SSCampeonato.ICampeonato;
import SimuladorLN.SSCampeonato.SSCampeonatoFacade;
import SimuladorLN.SSConta.IConta;
import SimuladorLN.SSConta.SSContaFacade;
import UserInterface.Menu.PreCondition;

import java.io.IOException;

public class SimuladorLNFacade implements ISimuladorLN {

    private ICarro carroFacade;
    private ICorrida corridaFacade;
    private IConta contaFacade;
    private ICampeonato campeonatoFacade;

    public SimuladorLNFacade() throws IOException {

        this.carroFacade = new SSCarroFacade();
        this.corridaFacade = new SSCorridaFacade();
        this.contaFacade = new SSContaFacade();
        this.campeonatoFacade = new SSCampeonatoFacade();
    }

    @Override
    public ICarro getCarroFacade() {

        return this.carroFacade;
    }

    @Override
    public ICampeonato getCampeonatoFacade() {

        return this.campeonatoFacade;
    }

    @Override
    public ICorrida getCorridaFacade() {

        return corridaFacade;
    }

    @Override
    public IConta getContaFacacde() {

        return contaFacade;
    }

    @Override
    public void start() {

    }

    // boolean login(String user,String password) throws IOException;

    @Override
    public void shutdown() {

    }

    @Override
    public boolean login(String user, String password) throws IOException {

        return false;
    }

    @Override
    public PreCondition existeConta() {
        // TODO Auto-generated method stub
        return null;
    }

}