package SimuladorLN.SSConta;

import java.util.Map;

public interface IConta {

	boolean verificaAfinacao(int nCorridas, int nAfinacoes);

	boolean verificarCredenciais(String user, String password);

	void configurarCampeonato(Map<String, Participante> participantes_camp, String idParticipante, String idCarro,
			String idPiloto);

	Conta criarConta(int id, String usern, String pass, String versao);

	void putConta(Conta c);

	Conta getConta(String idConta);
}