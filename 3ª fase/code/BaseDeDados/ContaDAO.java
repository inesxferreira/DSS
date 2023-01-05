package BaseDeDados;

import SimuladorLN.SSConta.Conta;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import static java.util.stream.Collectors.*;

public class ContaDAO implements Map<String, Conta> {

    private static ContaDAO singleton = null;

    private ContaDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS contas(" +
                    "identificador varchar(45) NOT NULL PRIMARY KEY," +
                    "score int(10) DEFAULT 0," +
                    "versaoPremium boolean DEFAULT false)";
            stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS ranking_global (" +
                    "conta_id varchar(10) DEFAULT NULL," +
                    "foreign key(conta_id) references contas(Identificador))" +
                    stm.executeUpdate(sql);
                    
            sql = "CREATE TABLE IF NOT EXISTS participantes(" +
                    "id varchar(10) NOT NULL PRIMARY KEY," +
                    "score_campeonato int(10) DEFAULT 0," +
                    "posicao int(3) DEFAULT 0," +
                    "id_conta varchar(45) DEFAULT NULL," +
                    "foreign key(id_conta) references contas(identificador))";
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