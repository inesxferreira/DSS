package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class GT_Hibrido extends GT implements Hibrido {

    private int potenciaE;

    public GT_Hibrido() {
        super();
        this.potenciaE = 0;
    }

    public GT_Hibrido(String idCarro, String modelo, String marca, String categoria, float pac, double fiabilidade,
            int tipoPneus, int modoMotor, int potencia, int potenciaC, int cilindrada, float deterioracao,
            int potenciaE) {
        super(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor, potencia, potenciaC,
                cilindrada, deterioracao);
        this.potenciaE = potenciaE;
    }

    public GT_Hibrido(GT_Hibrido p) {
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
        return new GT_Hibrido(this);
    }

}