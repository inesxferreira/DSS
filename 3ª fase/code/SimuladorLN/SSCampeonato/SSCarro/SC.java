package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public abstract class SC extends Carro {
    public SC() {
        super();
    }

    public SC(String idCarro, String modelo, String marca, String categoria, Float pac, int tipoPneus, int modoMotor,
            int potencia, int potenciaC, int cilindrada) {
        super(idCarro, modelo, marca, categoria, pac, 0.0f, tipoPneus, modoMotor, potencia, potenciaC, cilindrada);
    }

    public SC(SC p) {
        super(p);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        SC c = (SC) o;
        return (super.equals(c));
    }

    /*public Carro clone() {
        return new SC(this);                       // DA ERRO
    }*/

    /*
     * public boolean DNF(int volta, int totalvoltas, int chuva) {
     * Random rand = new Random();
     * int x = rand.nextInt(73);
     * Piloto p = null;
     * if (volta < totalvoltas / 2)
     * p = super.getEquipa().getPiloto1();
     * else
     * p = super.getEquipa().getPiloto2();
     * int qualidade;
     * if (chuva == 1)
     * qualidade = p.getQualidadeChuva();
     * else
     * qualidade = p.getQualidade();
     * // no maximo fiabilidade de 70%
     * int fiabilidade = (int) (qualidade * 0.75) + (int) ((super.getCilindrada() /
     * 10) * 0.25);
     * // System.out.println("Fiabilidade: "+fiabilidade);
     * // System.out.println("Random: "+x);
     * return (x > fiabilidade);
     * }
     */
}
