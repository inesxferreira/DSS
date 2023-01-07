package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class C1 extends Carro {
	private int nAfinacoes;

	public C1() {
		super();
		this.nAfinacoes = 0;
	}

	public C1(Integer idCarro, String marca, String modelo, String categoria,
			int potenciaC, int cilindrada) {
		super(idCarro, marca, modelo, categoria, potenciaC, cilindrada);
		// this.nAfinacoes = 0;
	}

	/*
	 * public C1(String idCarro, String modelo, String marca, String categoria,
	 * float pac, double fiabilidade,
	 * int tipoPneus, int modoMotor,
	 * int potencia, int potenciaC, int cilindrada, int nAfinacoes) {
	 * super(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus,
	 * modoMotor, potencia, potenciaC,
	 * cilindrada);
	 * this.nAfinacoes = nAfinacoes;
	 * }
	 */

	public C1(C1 p) {
		super(p);
		this.nAfinacoes = p.getAfinacoes();
	}

	public int getAfinacoes() {
		return this.nAfinacoes;
	}

	public void setAfinacoes(int nAfinacoes) {
		this.nAfinacoes = nAfinacoes;
	}

	public void setDownforce(float valor) {
		setPac(valor);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || this.getClass() != o.getClass())
			return false;

		C1 c = (C1) o;
		return (super.equals(c));
	}

	public Carro clone() {
		return new C1(this);
	}

	public void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva) {
		double fiabilidade = 0.95;
		this.setFiabilidade(fiabilidade);
	}

}