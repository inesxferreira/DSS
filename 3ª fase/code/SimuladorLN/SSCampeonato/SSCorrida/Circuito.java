package SimuladorLN.SSCampeonato.SSCorrida;

import java.util.HashMap;
import java.util.Map;

public class Circuito {

	private String idCircuito;
	private String nomeCircuito;
	private float distancia;
	private int nCurvas;
	private int nChicanes;
	private int nRetas;
	private Map<String,Caracteristica> caracteristicas;

	/* Construtores */
	public Circuito() {
		this.idCircuito = "";
		this.nomeCircuito = "";
		this.distancia = 0.0f;
		this.nCurvas = 0;
		this.nChicanes = 0;
		this.nRetas = 0;
		this.caracteristicas = new HashMap<>();
	}

	public Circuito(String idCircuito, String nomeCircuito, float distancia, int nCurvas, int nChicanes,
			Map<String,Caracteristica> carac) {
		this.idCircuito = idCircuito;
		this.nomeCircuito = nomeCircuito;
		this.distancia = distancia;
		this.nCurvas = nCurvas;
		this.nChicanes = nChicanes;
		for (Caracteristica c : carac.values()){
            this.caracteristicas.put(c.getIdCaracteristica(), c.clone());
        }
	}

	public Circuito(Circuito c) {
		this.idCircuito = c.getIdCircuito();
		this.nomeCircuito = c.getNomeCircuito();
		this.distancia = c.getDistancia();
		this.nCurvas = c.getNCurvas();
		this.nChicanes = c.getNChicanes();
		this.nRetas = c.getNRetas();
		this.caracteristicas = c.getCaracteristicas();
	}

	/**
	 * 
	 * @return Id do circuito
	 */
	public String getIdCircuito() {
		return this.idCircuito;
	}

	/**
	 * 
	 * @return Nome do circuito
	 */
	public String getNomeCircuito() {
		return this.nomeCircuito;
	}

	/**
	 * 
	 * @return distancia
	 */
	public float getDistancia() {
		return this.distancia;
	}

	/**
	 * 
	 * @return nCurvas
	 */
	public int getNCurvas() {
		return this.nCurvas;
	}

	/**
	 * 
	 * @return nChicanes
	 */
	public int getNChicanes() {
		return this.nChicanes;
	}

	/**
	 * 
	 * @return nRetas
	 */
	public int getNRetas() {
		return this.nRetas;
	}

	/**
	 * 
	 * @return caracteristicas
	 */
	public Map<String, Caracteristica> getCaracteristicas() {
		Map<String, Caracteristica> carac = new HashMap<>();
        for(String id : this.caracteristicas.keySet()){
            carac.put(id, this.caracteristicas.get(id).clone());
        }
        return carac;
	}

	/**
	 * 
	 * @param distancia
	 */
	public void setIdCircuito(String id) {
		this.idCircuito = id;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nomeCircuito = nome;
	}

	/**
	 * 
	 * @param distancia
	 */
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}

	/**
	 * 
	 * @param nCurvas
	 */
	public void setNCurvas(int nCurvas) {
		this.nCurvas = nCurvas;
	}

	/**
	 * 
	 * @param nChicanes
	 */
	public void setNChicanes(int nChicanes) {
		this.nChicanes = nChicanes;
	}

	/**
	 * 
	 * @param nRetas
	 */
	public void setNRetas(int nRetas) {
		this.nRetas = nRetas;
	}

	public void calcularNRetas() {
		nCurvas = getNCurvas();
		nChicanes = getNChicanes();
		nRetas = (nCurvas + nChicanes) * 2;
		setNRetas(nRetas);
	}

	/**
	 * 
	 * @param caracteristicas
	 */
	public void definirGDU(String idCaracteristica, float gdu) {
		//this.caracteristicas.values().stream().forEach(c->c.setGDU(gdu));
		this.caracteristicas.get(idCaracteristica).setGDU(gdu);
	}
}