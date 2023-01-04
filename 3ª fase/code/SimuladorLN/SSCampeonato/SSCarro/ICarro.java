package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class interface here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public interface ICarro {
	public Carro getCarro(String idCarro);
	public void putCarro(Carro c);
	public Piloto getPiloto(String idPiloto);
	public void putPiloto(Piloto p);
	public void calculaFiabilidade(String idCarro, int n_voltas, int cts, int sva);

}