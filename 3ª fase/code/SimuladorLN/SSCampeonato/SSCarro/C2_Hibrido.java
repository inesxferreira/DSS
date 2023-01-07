package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class C2_Hibrido extends C2 {

    private int potenciaE;

    public C2_Hibrido() {
        super();
        this.potenciaE = 0;
    }

    public C2_Hibrido(Integer idCarro, String marca, String modelo, String categoria, int potenciaC, int cilindrada,
            int potenciaE) {
        super(idCarro, marca, modelo, categoria, potenciaC, cilindrada);
        this.potenciaE = potenciaE;
    }

    public C2_Hibrido(C2_Hibrido p) {
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
        return new C2_Hibrido(this);
    }

}