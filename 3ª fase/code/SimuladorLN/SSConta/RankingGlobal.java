package SimuladorLN.SSConta;

import java.util.*;

public class RankingGlobal {

	private TreeMap<String, Conta> contas;

	/**
	 * 
	 * @param idConta
	 */
	public int getPosicao(String idConta) {
		int count = 1;
		for (String conta : contas.keySet()) {
			if (conta.equals(idConta))
				return count;
			count++;
		}
		if (count > 1)
			return -1;
		return count;
	}

	/*
	 * private void atualizaScoreGlobal() {
	 * // TODO - implement RankingGlobal.atualizaScoreGlobal
	 * throw new UnsupportedOperationException();
	 * }
	 */

}