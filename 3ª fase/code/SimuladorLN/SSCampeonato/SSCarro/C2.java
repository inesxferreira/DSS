package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class C2 here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class C2 extends Carro {
	private int nAfinacoes;

	public C2() {
		super();
		this.nAfinacoes = 0;
	}

	public C2(Integer idCarro, String marca, String modelo, String categoria, int potenciaC, int cilindrada) {
		super(idCarro, marca, modelo, categoria, potenciaC,
				cilindrada);
		// this.nAfinacoes = 0;
	}

	public C2(Integer idCarro, String marca, String modelo, String categoria, int potenciaC, int cilindrada,
			int nAfinacoes) {
		super(idCarro, marca, modelo, categoria, potenciaC, cilindrada);
		this.nAfinacoes = nAfinacoes;
	}

	public C2(C2 p) {
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

		C2 c = (C2) o;
		return (super.equals(c));
	}

	public Carro clone() {
		return new C2(this);
	}

	public void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva) {
		double fiabilidade = 0.8 * (1 - 0.2 * (5000 - this.getCilindrada()) / 2000);
		this.setFiabilidade(fiabilidade);
	}
}