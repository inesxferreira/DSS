package SimuladorLN.SSCampeonato;

import java.util.*;
import BaseDeDados.*;
import SimuladorLN.SSCampeonato.SSCorrida.*;

public class Campeonato {

	private Collection<CampeonatoDAO> campeonatoDAO;
	private String idCampeonato;
	TreeMap<String, Integer> scoreCamp = new TreeMap<String, Integer>();
	private String nome;

	public Campeonato() {
		this.nome = "";
		this.idCampeonato = "";

	}

	public Campeonato(Collection<CampeonatoDAO> campeonatoDAO, String idCampeonato, TreeMap<String, Integer> scoreCamp,
			String nome) {
		this.campeonatoDAO = campeonatoDAO;
		this.idCampeonato = idCampeonato;
		this.scoreCamp = scoreCamp;
		this.nome = nome;
	}

	public static String getNome() {
		return this.nome;
	}

	public static String getCampeonato() {
		return this.idCampeonato;
	}

	public Map<String, Circuito> getCircuitos() {

		throw new UnsupportedOperationException();
	}

	public void setCampeonatoDAO(Collection<CampeonatoDAO> campeonatoDAO) {
		this.campeonatoDAO = campeonatoDAO;
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

	public Collection<CampeonatoDAO> getCampeonatoDAO() {
		return campeonatoDAO;
	}

	public String getIdCampeonato() {
		return idCampeonato;
	}

	public Map<String, Integer> getScoreCamp() {
		return scoreCamp;
	}

	@Override
	public String toString() {
		return "Campeonato{" +
				"campeonatoDAO=" + campeonatoDAO +
				", idCampeonato='" + idCampeonato + '\'' +
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
		return Objects.equals(campeonatoDAO, that.campeonatoDAO) && Objects.equals(idCampeonato, that.idCampeonato)
				&& Objects.equals(scoreCamp, that.scoreCamp) && Objects.equals(nome, that.nome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(campeonatoDAO, idCampeonato, scoreCamp, nome);
	}
}