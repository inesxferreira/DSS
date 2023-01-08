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

    // Print da lista de carros
	public void listarCarros() {

		for (String carroId : todos_carros.keySet()) {
			System.out.println("\n[" + carroId + "]");
			detalhesCarro(carroId);
		}

	}

    private void detalhesCarro(String carroId) {
        Carro c = todos_carros.get(carroId);
        System.out.println("\tCategoria: " + c.getCategoria());
        System.out.print("\t" + c.getMarca());
        System.out.println(" " + c.getModelo());
        System.out.print("\t" + c.getCilindrada() + " cm³ - ");
        System.out.println(c.getPotenciaC() + " CV");
    }

    // Print da lista de pilotos
	public void listarPilotos() {

		for (String pilotoId : todos_pilotos.keySet()) {
			System.out.println("[" + pilotoId + "]\n");
			detalhesPiloto(pilotoId);
		}

	}

    private void detalhesPiloto(String pilotoId) {
        Piloto p = todos_pilotos.get(pilotoId);
        System.out.println("\t" + p.getNome());
        System.out.println("\tCTS: " + p.getCTS());
        System.out.println("\tSVA: " + p.getSVA());
    }

    /**
     * Método que devolve um carro dado o seu identificador.
     *
     * @param idCarro Código do carro.
     * @return Carro.
     */
    public Carro getCarro(Integer idCarro) {
        return this.todos_carros.get(idCarro);
    }

    /**
     * Método que permite adicionar um carro.
     *
     * @param c Carro a adicionar.
     */
    public void putCarro(Carro c) {
        this.todos_carros.put(c.getIdCarro().toString(), c);
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
        this.todos_pilotos.put(p.getIdPiloto().toString(), p);
    }


}