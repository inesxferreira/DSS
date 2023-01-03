package SimuladorLN.SSCampeonato.SSCarro;

public class C2 extends Carro {

	private int nAfinacoes;

	public C2(String idCarro, String modelo, String marca, String categoria, float pac, float fiabilidade, int tipoPneus,
            int modoMotor, int potencia, int potenciaC, int cilindrada) {
				super(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor, potencia, potenciaC, cilindrada);
    }

    /**
	 * 
	 * @param valor
	 */
	public void setDownforce(Float valor) {
		// TODO - implement C2.setDownforce
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param modo
	 */
	public void setMotor(int modo) {
		// TODO - implement C2.setMotor
		throw new UnsupportedOperationException();
	}

	@Override
	public Carro clone() {
		// TODO Auto-generated method stub
		return null;
	}

}