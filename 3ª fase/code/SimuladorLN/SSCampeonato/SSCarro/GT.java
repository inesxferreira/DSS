package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class GT here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public abstract class GT extends Carro {
    public GT() {
        super();
    }

    public GT(String idCarro, String modelo, String marca, String categoria, Float pac, int tipoPneus, int modoMotor,
            int potencia, int potenciaC, int cilindrada) {
        super(idCarro, modelo, marca, categoria, pac, 0.0f, tipoPneus, modoMotor, potencia, potenciaC, cilindrada);
    }

    public GT(GT p) {
        super(p);
    }

	

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        GT c = (GT) o;
        return (super.equals(c));
    }
}