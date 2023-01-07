package SimuladorLN.SSConta;

public class Conta implements Comparable<Conta> {

	private Integer idConta;
	private String username;
	private String password;
	private Boolean versaoPremium;
	private int scoreGlobal;
	// private Participante participante;

	public Conta() {
		this.idConta = 0;
		this.username = "";
		this.password = "";
		this.versaoPremium = false;
		this.scoreGlobal = 0;
	}

	public Conta(Integer idConta, String user, String pass, Boolean premium) {
		this.idConta = idConta;
		this.username = user;
		this.password = pass;
		this.versaoPremium = premium;
	}

	public Conta(Conta c) {
		this.idConta = c.getIdConta();
		this.username = c.getUsername();
		this.password = c.getPassword();
		this.versaoPremium = c.getVersaoPremium();
		this.scoreGlobal = c.getScoreGlobal();
	}

	public Integer getIdConta() {
		return this.idConta;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean getVersaoPremium() {
		return this.versaoPremium;
	}

	public int getScoreGlobal() {
		return this.scoreGlobal;
	}

	public void setIdConta(Integer id) {
		this.idConta = id;
	}

	public void setUsername(String usern) {
		this.username = usern;

	}

	public void setPassword(String password) {
		this.password = password;

	}

	public void setVersao(Boolean versao) {
		this.versaoPremium = versao;
	}

	public void setScoreGlobal(int i) {
		this.scoreGlobal = i;
	}

	/*
	 * int[] existeConta() {
	 * 
	 * }
	 */

	/**
	 * 
	 * @param nome
	 * @param password
	 */
	public boolean verificarCredenciais(String nome, String pass) {

		return nome.equals(this.idConta) && pass.equals(this.password);
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

	public int compareTo(Conta o) {
		return this.scoreGlobal - o.getScoreGlobal();
	}

	public Conta clone() {
		return new Conta(this);
	}

}