package SimuladorLN.SSConta;

import java.util.Map;
import java.util.Set;
import java.util.List;

import BaseDeDados.CarroDAO;
import BaseDeDados.ContaDAO;
import BaseDeDados.PilotoDAO;
import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class SSContaFacade implements IConta {

	private Map<String, Carro> todos_carros;
	private Map<String, Piloto> todos_pilotos;
	private Map<String, Conta> todas_contas;

	/**
	 * Construtor por omissão para objetos da classe SSContaFacade.
	 */
	public SSContaFacade() {
		this.todos_carros = CarroDAO.getInstance();
		this.todos_pilotos = PilotoDAO.getInstance();
		this.todas_contas = ContaDAO.getInstance();
	}

	/**
	 * Método que devolve uma conta dado o seu identificador.
	 *
	 * @param idConta id da conta.
	 * @return Conta.
	 */
	public Conta getConta(String idConta) {
		return this.todas_contas.get(idConta);
	}

	/**
	 * Método que permite adicionar uma conta.
	 *
	 * @param c Conta a adicionar.
	 */
	public void putConta(Conta c) {
		this.todas_contas.put(c.getIdConta().toString(), c.clone());
	}

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	public boolean verificaAfinacao(int nCorridas, int nAfinacoes) {
		if (nAfinacoes <= (2 / 3) * nCorridas)
			return true;
		return false;
	}

	/**
	 * @param idConta  Id da conta
	 * @param user     username da conta
	 * @param password password da conta
	 * 
	 * @return boolean validacao das credenciais
	 */
	@Override
	public boolean verificarCredenciais(String user, String password) {
		Set<Map.Entry<String, Conta>> c = todas_contas.entrySet();
		for (Map.Entry<String, Conta> entry : c) {
			if (entry.getValue().getUsername().equals(user) && entry.getValue().getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param participantes_camp Map ao qual queremos adicionar o participante
	 *                           criado
	 * @param idCarro            id do carro escolhido pelo participante
	 * @param idPiloto           id do piloto escolhido pelo participante
	 */
	public void configurarCampeonato(Map<String, Participante> participantes_camp, String idParticipante,
			String idCarro,
			String idPiloto) {
		// Ir buscar carros e pilotos à db de acordo com o id
		Carro carro = null;
		Piloto piloto = null;

		if (todos_carros.containsKey(idCarro)) {
			carro = todos_carros.get(idCarro);
		}

		if (todos_pilotos.containsKey(idPiloto)) {
			piloto = todos_pilotos.get(idPiloto);
		}

		// Inicializar participantes
		Participante participante = new Participante(idParticipante, piloto, carro, 0);

		// Adicionas o participante criado ao map do campeonato
		participantes_camp.put(participante.getIdParticipante(), participante);
	}

	@Override
	public Conta criarConta(int id, String usern, String pass, String versao) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param idConta
	 */
	/*
	 * public int getPosicaoRanking(String idConta) {
	 * boolean primeiro = true;
	 * 
	 * int count = 1;
	 * for (String conta : todas_contas.keySet()) {
	 * if (conta.equals(idConta)) {
	 * return count;
	 * }
	 * if (count == 1 && !conta.equals(idConta))
	 * primeiro = false;
	 * count++;
	 * }
	 * 
	 * if (count == 1 && !primeiro)
	 * return -1;
	 * return count;
	 * }
	 */

	/**
	 * 
	 * @param scoreDoCampeonato
	 */
	// PRECISO DE REVER ESTA MELHOR HA AQUI UMAS DUVIDAS !!!!! FALTA DEPOIS MUDAR A
	// POSICAO
	/*
	 * public void atualizaScore(String idConta, int scoreDoCampeonato) {
	 * for (Conta c : todas_contas.values())
	 * if (c.getIdConta().equals(idConta))
	 * todas_contas.get(idConta).setScore(todas_contas.get(idConta).getScoreGlobal()
	 * + scoreDoCampeonato);
	 * 
	 * }
	 */

}
