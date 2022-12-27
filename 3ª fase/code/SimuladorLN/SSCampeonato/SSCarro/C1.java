package SimuladorLN.SSCampeonato.SSCarro;

public abstract class C1 extends Carro {
	private int nAfinacoes;

	public C1()
    {
        super();
    }
    
    public C1(String idCarro, String modelo, String marca, String categoria, Float pac, int tipoPneus, int modoMotor,
            int potencia, int potenciaC, int cilindrada)
    {
        super(idCarro, modelo, marca, categoria, pac, 85.0f, tipoPneus, modoMotor, potencia, potenciaC, cilindrada);
    }
    
    public C1(C1 p)
    {
        super(p);
    }

	/**
	 * 
	 * @param valor
	 */
	public void setDownforce(Float valor) {
		setPac(valor);
	}

	/**
	 * 
	 * @param modo
	 */
	/*public void setMotor(int modo) {
		setModoMotor(modo);;
	}*/

	/**
	 * 
	 * @param f
	 */
	public void fiabilidade(Float f) {
		setFiabilidade(f);
	}

	public boolean equals(Object o)
    {
        if(this==o)
        return true;
        
        if(o==null || this.getClass()!=o.getClass())
        return false;
        
        C1 c = (C1) o;
        return (super.equals(c));
    }

}