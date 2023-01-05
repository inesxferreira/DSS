package SimuladorLN.SSConta;


public class Conta implements Comparable<Conta> {

	private int posicao;
	private String idConta;
	private String password;
	private Boolean versaoPremium;
	private int score;


	private String getIdConta() {
		return this.idConta;
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public boolean verificarCredenciais(String nome, String pass) {
		return nome.equals(this.idConta) && pass.equals(this.password);
	}


	public int getPosicao() {
		return this.posicao;
	}

	/**
	 * 
	 * @param posicao
	 * 
	 */
	public void setPosicao(int pos) {
		this.posicao = pos;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @param modo
	 */
	public void criarConta(String username, String password, boolean modo, int ultima_pos) {
		this.idConta = username;
		this.password = password;
		this.posicao = ultima_pos+1;
		this.versaoPremium = modo;
		this.score = 0;
	}

	public int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param posicao
	 * 
	 */
	private void setScore(int score) {
		this.score = score;
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

}