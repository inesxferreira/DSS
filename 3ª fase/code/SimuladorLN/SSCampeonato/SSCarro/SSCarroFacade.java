package SimuladorLN.SSCampeonato.SSCarro;

import java.util.Map;
import BaseDeDados.CarroDAO;
import BaseDeDados.PilotoDAO;

/**
 * Write a description of class Facade here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class SSCarroFacade implements ICarro {
    /**
     * Carros que existem no sistema
     */
    private Map<String, Carro> todos_carros;
    private Map<String, Piloto> todos_pilotos;

    /**
     * Construtor por omissão para objetos da classe SSCarroFacade.
     */
    public SSCarroFacade() {
        // this.carros =
        // carros.entrySet().stream().collect(Collectors.toMap(c->c.getKey(), c->
        // c.getValue().clone()));
        this.todos_carros = CarroDAO.getInstance();
        this.todos_pilotos = PilotoDAO.getInstance();
    }

    /**
     * Método que devolve um carro dado o seu identificador.
     *
     * @param idCarro Código do carro.
     * @return Carro.
     */
    public Carro getCarro(String idCarro) {
        return this.todos_carros.get(idCarro);
    }

    /**
     * Método que permite adicionar um carro.
     *
     * @param c Carro a adicionar.
     */
    public void putCarro(Carro c) {
        this.todos_carros.put(c.getIdCarro(), c);
    }

    /**
     * Método que devolve um Piloto dado o seu identificador.
     *
     * @param idPiloto Código do Piloto.
     * @return Piloto.
     */
    public Piloto getPiloto(String idPiloto) {
        return this.todos_pilotos.get(idPiloto);
    }

    /**
     * Método que permite adicionar um Piloto.
     *
     * @param c Piloto a adicionar.
     */
    public void putPiloto(Piloto p) {
        this.todos_pilotos.put(p.getIdPiloto(), p);
    }
}