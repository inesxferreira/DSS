package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class GT here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class GT extends Carro {
    private float deterioracao;

    public GT() {
        super();
        this.deterioracao = 0.0f;
    }

    public GT(String idCarro, String modelo, String marca, String categoria, float pac, double fiabilidade,
            int tipoPneus, int modoMotor, int potencia, int potenciaC, int cilindrada, float deterioracao) {
        super(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor, potencia, potenciaC,
                cilindrada);
        this.deterioracao = deterioracao;
    }

    public GT(GT p) {
        super(p);
        this.deterioracao = p.getDeterioracao();
    }

    public float getDeterioracao() {
        return this.deterioracao;
    }

    public void setDeterioracao(float taxa) {
        this.deterioracao = taxa;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        GT c = (GT) o;
        return (super.equals(c));
    }

    public Carro clone() {
        return new GT(this);
    }
}
