package SimuladorLN.SSCampeonato;

import java.util.*;
import SimuladorLN.SSCampeonato.SSCorrida.*;

public class Campeonato {
	private String idCampeonato;
	private String nome;
	TreeMap<String, Integer> scoreCamp = new TreeMap<String, Integer>();
	private Map<String, Circuito> circuitos = new HashMap<String, Circuito>();

	public Campeonato() {
		this.nome = "";
		this.idCampeonato = "";

	}

	public Campeonato(String idCampeonato, String nome) {
		this.idCampeonato = idCampeonato;
		this.nome = nome;
	}

	public Campeonato(String idCampeonato, String nome, Map<String,Circuito> circuitos) {
		this.idCampeonato = idCampeonato;
		this.nome = nome;
		this.circuitos = circuitos;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCampeonato() {
		return this.idCampeonato;
	}

	public Map<String, Circuito> getCircuitos() {

		return this.circuitos;
	}

	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public void setScoreCamp(TreeMap<String, Integer> scoreCamp) {
		this.scoreCamp = scoreCamp;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdCampeonato() {
		return idCampeonato;
	}

	public Map<String, Integer> getScoreCamp() {
		return scoreCamp;
	}

	@Override
	public String toString() {
		return "Campeonato{idCampeonato='" + idCampeonato + '\'' +
				", scoreCamp=" + scoreCamp +
				", nome='" + nome + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Campeonato that = (Campeonato) o;
		return Objects.equals(idCampeonato, that.idCampeonato)
				&& Objects.equals(scoreCamp, that.scoreCamp) && Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCampeonato, scoreCamp, nome);
	}
}