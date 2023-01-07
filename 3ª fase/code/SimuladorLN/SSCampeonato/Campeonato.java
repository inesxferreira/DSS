package SimuladorLN.SSCampeonato;

import java.util.*;
import SimuladorLN.SSCampeonato.SSCorrida.*;
import SimuladorLN.SSConta.Participante;

public class Campeonato {
	private String idCampeonato;
	private String nome;
	TreeMap<String, Integer> scoreCamp = new TreeMap<String, Integer>();
	private Map<String, Corrida> corridas = new HashMap<String, Corrida>();
	private Map<String, Participante> participantes = new HashMap<String, Participante>();

	public Campeonato() {
		this.nome = "";
		this.idCampeonato = "";
		this.scoreCamp = new TreeMap<>();
		this.corridas = new HashMap<>();
		this.participantes = new HashMap<>();
	}

	public Campeonato(String idCampeonato, String nome) {
		this.idCampeonato = idCampeonato;
		this.nome = nome;
	}

	public Campeonato(String idCampeonato, String nome, Map<String, Participante> participantes) {
		this.idCampeonato = idCampeonato;
		this.nome = nome;

		for (Participante p : participantes.values()) {
			this.participantes.put(p.getIdParticipante(), p.clone());
		}
	}

	public Campeonato(Campeonato c) {
		this.idCampeonato = c.getIdCampeonato();
		this.nome = c.getNome();
		this.scoreCamp = c.getScoreCamp();
		this.corridas = c.getCorridas();
	}

	public String getNome() {
		return this.nome;
	}

	public String getCampeonato() {
		return this.idCampeonato;
	}

	public TreeMap<String, Integer> getScoreCamp() {
		return this.scoreCamp;
	}

	public Map<String, Circuito> getCircuitos() {
		Map<String, Circuito> circ = new HashMap<>();
		for (String id : this.circuitos.keySet()) {
			circ.put(id, this.circuitos.get(id).clone());
		}
		return circ;
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

	public Campeonato clone() {
		return new Campeonato(this);
	}

}