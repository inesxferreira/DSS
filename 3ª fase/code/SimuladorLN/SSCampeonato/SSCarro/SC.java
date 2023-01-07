package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class SC here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public class SC extends Carro {
    public SC() {
        super();
    }

    public SC(Integer idCarro, String marca, String modelo, String categoria, int potenciaC, int cilindrada) {
        super(idCarro, marca, modelo, categoria, potenciaC, cilindrada);
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

    @Override
    public SC clone() {
        return new SC(this);
    }

    public void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva) {
        double fiabilidade = 0.75 * (1 - 0.2 * (2500 - this.getCilindrada()) / 500)
                * (1 - 0.2 * (100 - (cts + sva)) / 100);
        this.setFiabilidade(fiabilidade);
    }

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
     * elsed
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
