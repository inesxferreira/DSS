package SimuladorLN.SSConta;

import java.util.*;

public class RankingGlobal {

	private TreeMap<String, Conta> contas;

	/**
	 * 
	 * @param idConta
	 */
	public int getPosicaoRanking(String idConta) {
		int count = 1;
		for (String conta : contas.keySet()) {
			if (conta.equals(idConta)){
				contas.get(idConta).setPosicao(count);
				return count;
			}
			count++;
		}
		if (count > 1)
			return -1;
		return count;
	}

	/**
	 * 
	 * @param scoreDoCampeonato
	 */
	// PRECISO DE REVER ESTA MELHOR HA AQUI UMAS DUVIDAS !!!!! FALTA DEPOIS MUDAR A
	// POSICAO
	public void atualizaScore(String idConta,int scoreDoCampeonato) {
		contas.get(idConta).setScore(contas.get(idConta).getScore()+scoreDoCampeonato);
		for(Conta c : contas.values())
			c.get(c.getIdConta()).setPosicao(getPosicaoRanking(id));
	}

	public int totalDeContas(){
		return contas.size();
	}

}