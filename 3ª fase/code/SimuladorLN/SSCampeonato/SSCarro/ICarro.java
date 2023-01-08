package SimuladorLN.SSCampeonato.SSCarro;

/**
 * Write a description of class interface here.
 * 
 * @author Marta Sa
 * @version 26122022
 */

public interface ICarro {

	public void listarCarros();

	public void listarPilotos();

	public Carro getCarro(Integer idCarro);

	public void putCarro(Carro c);

	public Piloto getPiloto(String idPiloto);

	public void putPiloto(Piloto p);

}