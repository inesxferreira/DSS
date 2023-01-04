package SimuladorLN.SSConta;

import BaseDeDados.*;
import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class Participante implements Comparable<Conta> {

	private PilotoDAO pilotoDAO;
	private CarroDAO carroDAO;
	private String idParticipante;
	private int scoreCampeonato;
	private int fatorOrd;

	/**
	 * 
	 * @param c
	 */
	public void setCarro(Carro c) {
		this.carroDAO = new CarroDAO(c.clone());
	}

	public Carro getCarro() {
		return new Carro(this.carroDAO);
	}

	/**
	 * 
	 * @param p
	 */
	public void setPiloto(Piloto p) {
		this.pilotoDAO = new PilotoDAO(p.clone());
	}

	public Piloto getPiloto() {
		return new Piloto(this.pilotoDAO);
	}

	public void configuraCampeonato() {
		// TODO - implement Participante.configuraCampeonato
		throw new UnsupportedOperationException();
	}
	public int compareTo(Participante o) {
        return this.scoreCampeonato-o.getScoreCampeonato();
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

}