package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class C1_Hibrido extends C1 implements Hibrido {
    private int potenciaE;

    public C1_Hibrido() {
        super();
        this.potenciaE = 0;
    }

    public C1_Hibrido(String idCarro, String modelo, String marca, String categoria, float pac, double fiabilidade,
            int tipoPneus,
            int modoMotor, int potencia, int potenciaC, int cilindrada, int nAfinacoes, int potenciaE) {
        super(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor, potencia, potenciaC,
                cilindrada,
                nAfinacoes);
        this.potenciaE = potenciaE;
    }

    public C1_Hibrido(C1_Hibrido p) {
        super(p);
        this.potenciaE = p.getPotenciaE();
    }

    @Override
    public int getPotenciaE() {
        return this.potenciaE;
    }

    @Override
    public void setPotenciaE(int potenciaE) {
        this.potenciaE = potenciaE;
    }

    @Override
    public Carro clone() {
        return new C1_Hibrido(this);
    }

}