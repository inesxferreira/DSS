package BaseDeDados;

import SimuladorLN.SSConta.RankingGlobal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import static java.util.stream.Collectors.*;

public class RankingGlobalDAO implements Map<String, RankingGlobal> {

    private static ContaDAO singleton = null;

    private ContaDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {

            sql = "CREATE TABLE IF NOT EXISTS ranking_global (" +
                    "IdConta VARCHAR(10) DEFAULT NULL," +
                    "Score INT(10) DEFAULT 0," +
                    "FOREIGN KEY(IdConta) REFERENCES contas(IdConta))" +
                    stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static ContaDAO getInstance() {
        if (ContaDAO.singleton == null) {
            ContaDAO.singleton = new ContaDAO();
        }
        return ContaDAO.singleton;
    }

    

}