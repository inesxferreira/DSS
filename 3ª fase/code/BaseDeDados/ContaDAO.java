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
                    "foreign key(conta_id) references contas(Identificador))"+
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

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Entry<String, Conta>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Conta get(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Conta put(String key, Conta c) {
        Conta res = null;
        //Sala s = c.getConta();
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {

            /* Actualizar o ranking
            stm.executeUpdate(
                    "INSERT INTO salas " +
                                "VALUES ('"+ s.getNumero()+ "', '"+
                                            s.getEdificio()+"', "+
                                            s.getCapacidade()+") " +
                                "ON DUPLICATE KEY UPDATE Edificio=Values(Edificio), " +
                                                        "Capacidade=Values(Capacidade)");
            */
            // Actualizar a Conta
            stm.executeUpdate("INSERT INTO contas VALUES ('"+c.getId()+"', '"+c.getScore() +"', '"+c.getVersao(c.getId)+"') ");

            /* Actualizar os alunos da turma
            Collection<String> oldAl = getAlunosTurma(key, stm);
            Collection<String> newAl = t.getAlunos();
            newAl.removeAll(oldAl);         // Alunos que entram na turma, em relação ao que está na BD
            oldAl.removeAll(t.getAlunos()); // Alunos que saem da turma, em relação ao que está na BD
            try (PreparedStatement pstm = conn.prepareStatement("UPDATE alunos SET Turma=? WHERE Num=?")) {
                // Remover os que saem da turma (colocar a NULL a coluna que diz qual a turma dos alunos)
                pstm.setNull(1, Types.VARCHAR);
                for (String a: oldAl) {
                    pstm.setString(2, a);
                    pstm.executeUpdate();
                }
                // Adicionar os que entram na turma (colocar o Id da turma na coluna Turma da tabela alunos)
                pstm.setString(1, t.getId());
                for (String a: newAl) {
                    pstm.setString(2, a);
                    pstm.executeUpdate();
                }
            }
            
            Vai faltar aqui um }    */

        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Conta> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public Conta remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT count(*) FROM contas")) {
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public Collection<Conta> values() {
        // TODO Auto-generated method stub
        return null;
    }
}