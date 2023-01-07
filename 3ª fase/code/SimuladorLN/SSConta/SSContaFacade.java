package SimuladorLN.SSConta;

import java.util.Map;
import BaseDeDados.*;
import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class SSContaFacade implements IConta {

	// private ContaDAO todas_contas;
	private Map<String, Conta> todas_contas;

	/**
	 * Construtor por omissão para objetos da classe SSContaFacade.
	 */
	public SSContaFacade() {
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
		this.todas_contas.put(c.getIdConta(), c.clone());
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
	 * 
	 * @param idParticipante
	 */
	public boolean getVersao(String idParticipante) {
		return todas_contas.get(idParticipante).getVersaoPremium();
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public void fazerLogin(String nome, String password) {
		boolean res = false;
		for (Conta conta : todas_contas) {
			if (conta.getIdConta().equals(nome) && conta.getPassword().equals(password)) {
				res = true;
				break;
			}
		}
	}

	/**
	 * 
	 * @param participantes_camp Map ao qual queremos adicionar o participante
	 *                           criado
	 * @param idCarro            id do carro escolhido pelo participante
	 * @param idPiloto           id do piloto escolhido pelo participante
	 */
	public void configuraCampeonato(Map<String, Participante> participantes_camp, String idParticipante, String idCarro,
			String idPiloto) {
		// Ir buscar carros e pilotos à db de acordo com o id
		Carro carro = null;
		Piloto piloto = null;
		if (CarroDAO.containsKey(idCarro)) {
			carro = CarroDAO.get(idCarro);
		}

		if (PilotoDAO.containsKey(idPiloto)) {
			piloto = PilotoDAO.get(idPiloto);
		}

		// Inicializar participantes
		Participante participante = new Participante(idParticipante, piloto, carro, 0, 0);

		// Adicionas o participante criado ao map do campeonato

	}

	/**
	 * 
	 * @param idConta
	 */
	public int getPosicaoRanking(String idConta) {
		boolean primeiro = true;

		int count = 1;
		for (String conta : todas_contas.keySet()) {
			if (conta.equals(idConta)) {
				return count;
			}
			if (count == 1 && !conta.equals(idConta))
				primeiro = false;
			count++;
		}

		if (count == 1 && !primeiro)
			return -1;
		return count;
	}

	/**
	 * 
	 * @param scoreDoCampeonato
	 */
	// PRECISO DE REVER ESTA MELHOR HA AQUI UMAS DUVIDAS !!!!! FALTA DEPOIS MUDAR A
	// POSICAO
	public void atualizaScore(String idConta, int scoreDoCampeonato) {
		for (Conta c : todas_contas.values())
			if (c.getIdConta().equals(idConta))
				todas_contas.get(idConta).setScore(todas_contas.get(idConta).getScoreGlobal() + scoreDoCampeonato);

	}
}
