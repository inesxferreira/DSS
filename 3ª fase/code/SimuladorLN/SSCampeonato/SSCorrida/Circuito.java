package SimuladorLN.SSCampeonato.SSCorrida;

import java.util.ArrayList;
import java.util.List;

public class Circuito {

	private Integer idCircuito;
	private String nomeCircuito;
	private float distancia;
	private int nCurvas;
	private int nChicanes;
	private int nRetas;
	private List<Caracteristica> caracteristicas;

	/* Construtores */
	public Circuito() {
		this.idCircuito = 0;
		this.nomeCircuito = "";
		this.distancia = 0.0f;
		this.nCurvas = 0;
		this.nChicanes = 0;
		this.nRetas = 0;
		this.caracteristicas = new ArrayList<Caracteristica>();
	}

	public Circuito(Integer idCircuito, String nomeCircuito, float distancia, int nCurvas, int nChicanes,
			List<Caracteristica> carac) {
		this.idCircuito = idCircuito;
		this.nomeCircuito = nomeCircuito;
		this.distancia = distancia;
		this.nCurvas = nCurvas;
		this.nChicanes = nChicanes;
		for (Caracteristica c : carac) {
			this.caracteristicas.add(c.clone());
		}
	}

	public Circuito(Integer idCircuito, String nomeCircuito, float distancia, int nCurvas, int nChicanes, int nRetas) {
		this.idCircuito = idCircuito;
		this.nomeCircuito = nomeCircuito;
		this.distancia = distancia;
		this.nCurvas = nCurvas;
		this.nChicanes = nChicanes;
		this.nRetas = nRetas;
		this.caracteristicas = new ArrayList<Caracteristica>();
	}

	public Circuito(Circuito c) {
		this.idCircuito = c.getIdCircuito();
		this.nomeCircuito = c.getNomeCircuito();
		this.distancia = c.getDistancia();
		this.nCurvas = c.getnCurvas();
		this.nChicanes = c.getnChicanes();
		this.nRetas = c.getnRetas();
		this.caracteristicas = c.getCaracteristicas();
	}

	/**
	 * 
	 * @return Id do circuito
	 */
	public Integer getIdCircuito() {
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
	public int getnCurvas() {
		return this.nCurvas;
	}

	/**
	 * 
	 * @return nChicanes
	 */
	public int getnChicanes() {
		return this.nChicanes;
	}

	/**
	 * 
	 * @return nRetas
	 */
	public int getnRetas() {
		return this.nRetas;
	}

	/**
	 * 
	 * @return caracteristicas
	 */
	public List<Caracteristica> getCaracteristicas() {
		List<Caracteristica> carac = new ArrayList<>();
		for (Caracteristica c : this.caracteristicas) {
			carac.add(c.clone());
		}
		return carac;
	}

	/**
	 * 
	 * @param distancia
	 */
	public void setIdCircuito(Integer id) {
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
	public void setnCurvas(int nCurvas) {
		this.nCurvas = nCurvas;
	}

	/**
	 * 
	 * @param nChicanes
	 */
	public void setnChicanes(int nChicanes) {
		this.nChicanes = nChicanes;
	}

	/**
	 * 
	 * @param nRetas
	 */
	public void setnRetas(int nRetas) {
		this.nRetas = nRetas;
	}

	public void calcularNRetas() {
		nCurvas = getnCurvas();
		nChicanes = getnChicanes();
		nRetas = (nCurvas + nChicanes) * 2;
		setnRetas(nRetas);
	}

	public void addCaracteristica(Caracteristica c){
		this.caracteristicas.add(c);
	}

	/**
	 * 
	 * @param caracteristicas
	 */
	/*
	 * public void definirGDU(String idCaracteristica, float gdu) {
	 * // this.caracteristicas.values().stream().forEach(c->c.setGDU(gdu));
	 * this.caracteristicas.get(idCaracteristica).setGDU(gdu);
	 * }
	 */

	public Circuito clone() {
		return new Circuito(this);
	}

}