package SimuladorLN.SSCampeonato;

import java.util.*;
import BaseDeDados.*;
import SimuladorLN.SSCampeonato.SSCorrida.*;

public class Campeonato {

	private Collection<CampeonatoDAO> campeonatoDAO;
	private String idCampeonato;
	private TreeSet<String, Int> scoreCamp;
	private String nome;

	public String getNome() {
		return this.nome;
	}

	public Map<String, Circuito> getCircuitos() {
		// TODO - implement Campeonato.getCircuitos
		throw new UnsupportedOperationException();
	}

}