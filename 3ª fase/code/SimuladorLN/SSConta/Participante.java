package SimuladorLN.SSConta;

import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class Participante implements Comparable<Participante> {

	private String idParticipante;
	private Piloto piloto;
	private Carro carro;
	private int scoreCampeonato;
	private int fatorOrd;

	public Participante() {
		this.idParticipante = "";
		this.piloto = null;
		this.carro = null;
		this.scoreCampeonato = 0;
		this.fatorOrd = 0;
	}

	public Participante(String idParticipante, Piloto p, Carro c, int score, int fator) {
		this.idParticipante = idParticipante;
		this.piloto = p.clone();
		this.carro = c.clone();
		this.scoreCampeonato = score;
		this.fatorOrd = fator;
	}

	public Participante(Participante c) {
		this.idParticipante = c.getIdParticipante();
		this.piloto = c.getPiloto();
		this.carro = c.getCarro();
		this.scoreCampeonato = c.getScoreCampeonato();
		this.fatorOrd = c.getFatorOrd();
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

	public int getFatorOrd() {
		return this.fatorOrd;
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

	public void setFatorOrd(int fatorOrd) {
		this.fatorOrd = fatorOrd;
	}

	public void configuraCampeonato() {
		// TODO - implement Participante.configuraCampeonato
		throw new UnsupportedOperationException();
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