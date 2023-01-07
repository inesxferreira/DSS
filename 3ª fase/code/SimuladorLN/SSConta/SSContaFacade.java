package SimuladorLN.SSConta;

import BaseDeDados.*;
import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class SSContaFacade implements IConta {

	private ContaDAO todas_contas;

	public int getScore() {
		// TODO - implement SSContaFacade.getScore
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	public void verificaAfinacao(int nCorridas, int nAfinacoes) {
		// TODO - implement SSContaFacade.verificaAfinacao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idParticipante
	 */
	public int getVersao(String idParticipante) {
		// TODO - implement SSContaFacade.getVersao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public void fazerLogin(String nome, String password) {
		// TODO - implement SSContaFacade.fazerLogin
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	public void setCarro(Carro c) {
		// TODO - implement SSContaFacade.setCarro
		throw new UnsupportedOperationException();
	}

	public Carro getCarro() {
		// TODO - implement SSContaFacade.getCarro
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param p
	 */
	public void setPiloto(Piloto p) {
		// TODO - implement SSContaFacade.setPiloto
		throw new UnsupportedOperationException();
	}

	public Piloto getPiloto() {
		// TODO - implement SSContaFacade.getPiloto
		throw new UnsupportedOperationException();
	}

	public void configuraCampeonato() {
		// TODO - implement SSContaFacade.configuraCampeonato
		throw new UnsupportedOperationException();
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
				// contas.get(idConta).setPosicao(count);
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
		// todas_contas.get(idConta).setScore(todas_contas.get(idConta).getScore() +
		// scoreDoCampeonato);
		for (Conta c : todas_contas.values())
			if (c.getIdConta().equals(idConta))
				todas_contas.get(idConta).setScore(todas_contas.get(idConta).getScore() + scoreDoCampeonato);

	}
}
