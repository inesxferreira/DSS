package SimuladorLN.SSConta;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class Participante implements Comparable<Participante> {

	private String idParticipante;
	private Piloto piloto;
	private Carro carro;
	private int scoreCampeonato;

	public Participante() {
		this.idParticipante = "";
		this.piloto = null;
		this.carro = null;
		this.scoreCampeonato = 0;
	}

	public Participante(String idParticipante, Piloto p, Carro c, int score) {
		this.idParticipante = idParticipante;
		this.piloto = p.clone();
		this.carro = c.clone();
		this.scoreCampeonato = score;
	}

	public Participante(Participante c) {
		this.idParticipante = c.getIdParticipante();
		this.piloto = c.getPiloto();
		this.carro = c.getCarro();
		this.scoreCampeonato = c.getScoreCampeonato();
	}

	public String getIdParticipante() {
		return this.idParticipante;
	}

	public Piloto getPiloto() {
		return this.piloto.clone();
	}

	public Carro getCarro() {
		return this.carro.clone();
	}

	public int getScoreCampeonato() {
		return this.scoreCampeonato;
	}

	public void setIdParticipante(String id) {
		this.idParticipante = id;
	}

	/**
	 * 
	 * @param p
	 */
	public void setPiloto(Piloto p) {
		this.piloto = p.clone();
	}

	/**
	 * 
	 * @param c
	 */
	public void setCarro(Carro c) {
		this.carro = c.clone();
	}

	public void setScoreCampeonato(int score) {
		this.scoreCampeonato = score;
	}

	public int compareTo(Participante o) {
		return this.scoreCampeonato - o.getScoreCampeonato();
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

	public Participante clone() {
		return new Participante(this);
	}
}