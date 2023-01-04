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
	public SSCarroFacade(Map<String,Carro> carros){
        //this.carros = carros.entrySet().stream().collect(Collectors.toMap(c->c.getKey(), c-> c.getValue().clone()));
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

	/**
     * Método que calcula e atribui uma fiabilida a um carro.
     *
     * @param idCarro identificador do carro.
     * @param n_voltas número de voltas da corrida.
     * @param cts habilidade chuva vs tempo seco do piloto.
     * @param sva agressividade do piloto.
	 */
	public void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva) {
		double fiabilidade;
		Carro carro = getCarro(idCarro);

		switch (carro.getCategoria()) {
			case "C1":
				fiabilidade = 0.95;
				break;
			case "C2":
				fiabilidade = 0.8 * (1 - 0.2 * (5000 - carro.getCilindrada()) / 2000);
				break;
			case "GT":
				fiabilidade = 0.7 * Math.pow(0.99, n_voltas) * (1 - 0.2 * (4000 - carro.getCilindrada()) / 2000);
				break;
			case "SC":
				fiabilidade = 0.75 * (1 - 0.2 * (2500 - carro.getCilindrada()) / 500)
						* (1 - 0.2 * (100 - (cts + sva)) / 100);
				break;
			default:
				fiabilidade = 0;
				break;
		}
		carro.setFiabilidade(fiabilidade);
	}
}