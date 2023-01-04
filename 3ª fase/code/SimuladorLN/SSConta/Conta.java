package SimuladorLN.SSConta;

public class Conta implements Comparable<Conta> {

	private RankingGlobal posicao;
	private String idConta;
	private String password;
	private Boolean versaoPremium;
	private int score;

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	private boolean verificarCredenciais(String nome, String pass) {
		return nome.equals(this.idConta) && pass.equals(this.password);
	}

	/**
	 * 
	 * @param scoreDoCampeonato
	 */
	// PRECISO DE REVER ESTA MELHOR HA AQUI UMAS DUVIDAS !!!!! FALTA DEPOIS MUDAR A
	// POSICAO
	private void atualizaScore(int scoreDoCampeonato) {
		this.score += scoreDoCampeonato;
		this.posicao.replace(idConta);
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

	public int getScore() {
		return this.score;
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
		return this.versaoPremium;
	}

	public int compareTo(Conta o) {
		return this.score - o.getScore();
	}
	/*
	 * 
	 * @param nome
	 * 
	 * @param password
	 *
	 * public void fazerLogin(String nome, String password) {
	 * // TODO - implement Conta.fazerLogin
	 * throw new UnsupportedOperationException();
	 * }
	 */

}