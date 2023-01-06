package SimuladorLN.SSConta;

public class Conta implements Comparable<Conta> {

	private String idConta;
	private String password;
	private Boolean versaoPremium;
	private int score;

	public Conta() {
		this.idConta = "";
		this.password = "";
		this.versaoPremium = false;
		this.score = 0;
	}

	public Conta(String idConta, String pass, Boolean premium, int score) {
		this.idConta = idConta;
		this.password = pass;
		this.versaoPremium = premium;
		this.score = score;
	}

	public Conta(Conta c) {
		this.idConta = c.getIdConta();
		this.password = c.getPassword();
		this.versaoPremium = c.getVersaoPremium();
		this.score = c.getScore();
	}

	String getIdConta() {
		return this.idConta;
	}

	private String getPassword() {
		return this.password;
	}

	private boolean getVersaoPremium() {
		return this.versaoPremium;
	}

	int getScore() {
		return this.score;
	}

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public boolean verificarCredenciais(String nome, String pass) {
		return nome.equals(this.idConta) && pass.equals(this.password);
	}

	/*
	 * public int getPosicao() {
	 * return this.posicao;
	 * }
	 */

	/**
	 * 
	 * @param posicao
	 * 
	 * 
	 *                public void setPosicao(int pos) {
	 *                this.posicao = pos;
	 *                }
	 */

	/**
	 * 
	 * @param username
	 * @param password
	 * @param modo
	 */
	/*
	 * public Conta(String username, String password, boolean modo, int ultima_pos)
	 * {
	 * this.idConta = username;
	 * this.password = password;
	 * this.posicao = ultima_pos + 1;
	 * this.versaoPremium = modo;
	 * this.score = 0;
	 * }
	 */

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

	public int compareTo(Conta o) {
		return this.score - o.getScore();
	}

	public void setScore(int i) {
	}

}