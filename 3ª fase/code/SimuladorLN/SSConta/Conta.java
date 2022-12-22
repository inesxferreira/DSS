package SimuladorLN.SSConta;

public class Conta {

	private RankingGlobal posicao;
	private Int score;
	private String idConta;
	private String password;
	private Boolean versaoPremium;

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	private Boolean verificarCredenciais(String nome, String password) {
		// TODO - implement Conta.verificarCredenciais
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param scoreDoCampeonato
	 */
	private void atualizaScore(Int scoreDoCampeonato) {
		// TODO - implement Conta.atualizaScore
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param modo
	 */
	public String criarConta(String username, String password, String modo) {
		// TODO - implement Conta.criarConta
		throw new UnsupportedOperationException();
	}

	public Int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param nCorridas
	 * @param nAfinacoes
	 */
	public void verificaAfinacao(Int nCorridas, Int nAfinacoes) {
		// TODO - implement Conta.verificaAfinacao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idParticipante
	 */
	public Int getVersao(String idParticipante) {
		// TODO - implement Conta.getVersao
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public void fazerLogin(String nome, String password) {
		// TODO - implement Conta.fazerLogin
		throw new UnsupportedOperationException();
	}

}