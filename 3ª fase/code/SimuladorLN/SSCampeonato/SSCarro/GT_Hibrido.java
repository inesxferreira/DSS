package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class GT_Hibrido extends GT {

    private int potenciaE;

    public GT_Hibrido() {
        super();
        this.potenciaE = 0;
    }

    public GT_Hibrido(Integer idCarro, String marca, String modelo, String categoria, int potenciaC, int cilindrada,
            int potenciaE) {
        super(idCarro, marca, modelo, categoria, potenciaC, cilindrada);
        this.potenciaE = potenciaE;
    }

    public GT_Hibrido(GT_Hibrido p) {
        super(p);
        this.potenciaE = p.getPotenciaE();
    }

    public int getPotenciaE() {
        return this.potenciaE;
    }

    public void setPotenciaE(int potenciaE) {
        this.potenciaE = potenciaE;
    }

    @Override
    public Carro clone() {
        return new GT_Hibrido(this);
    }

}