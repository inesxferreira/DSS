package BaseDeDados;

import SimuladorLN.SSConta.RankingGlobal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Set;
import java.sql.*;

public class RankingGlobalDAO implements Map<String, RankingGlobal> {

    private static RankingGlobalDAO singleton = null;

    private RankingGlobalDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS ranking_global (" +
                    "IdConta INT DEFAULT NOT NULL," +
                    "Score INT(10) DEFAULT 0," +
                    "FOREIGN KEY(IdConta) REFERENCES contas(IdConta))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar scoreGlobal...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Implementação do padrão Singleton
     *
     * @return devolve a instância única desta classe
     */
    public static RankingGlobalDAO getInstance() {
        if (RankingGlobalDAO.singleton == null) {
            RankingGlobalDAO.singleton = new RankingGlobalDAO();
        }
        return RankingGlobalDAO.singleton;
    }

    @Override
    public void clear() {
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM ranking_global WHERE IdConta = ?")) {
            for (String id : this.keySet()) {
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            // Erro ao remover ranking_global...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsKey(Object key) {
        boolean containsKey = false;
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT IdConta FROM ranking_global WHERE IdConta = ?")) {
            stm.setString(1, key.toString());
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    containsKey = true;
                }
            }
        } catch (SQLException e) {
            // Erro ao verificar se existe conta com o id especificado...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return containsKey;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof RankingGlobal) {
            RankingGlobal scoreGlobal = (RankingGlobal) value;
            try (Connection conn = DAOconfig.getConnection();
                    PreparedStatement stm = conn.prepareStatement(
                            "SELECT * FROM contas WHERE IdConta = ? AND Score = ?")) {
                stm.setString(1, scoreGlobal.getIdConta());
                stm.setInt(2, scoreGlobal.getScore());
                try (ResultSet rs = stm.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro a verificar existência da conta na base de dados", e);
            }
        }
        return false;
    }

    @Override
    public Set<Map.Entry<String, RankingGlobal>> entrySet() {
        Set<Map.Entry<String, RankingGlobal>> entries = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT * FROM ranking_global");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String idConta = rs.getString("IdConta");
                int score = rs.getInt("Score");

                RankingGlobal scoreGlobal = new RankingGlobal(idConta, score);
                entries.add(new AbstractMap.SimpleEntry<>(idConta, scoreGlobal));
            }
        } catch (SQLException e) {
            // Erro ao selecionar contas...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return entries;
    }

    @Override
    public RankingGlobal get(Object key) {
        RankingGlobal scoreGlobal = null;
        String sql = "SELECT * FROM ranking_global WHERE IdConta = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, (String) key); //// Eu tenho duvidas se aqui nao devemos fazer Set para cada parametro
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String idConta = rs.getString("IdConta");
                    int score = rs.getInt("Score");
                    scoreGlobal = new RankingGlobal(idConta, score);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return scoreGlobal;
    }

    @Override
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM ranking_global";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                } else {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        String sql = "SELECT IdConta FROM scoreGlobal";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                set.add(rs.getString("IdConta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public RankingGlobal put(String id, RankingGlobal scoreGlobal) {
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO ranking_global (IdConta,UserName,Password,VersaoPremium) VALUES (?,?)")) {
            stm.setString(1, id);
            stm.setInt(2, scoreGlobal.getScore());
            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a inserir score...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return scoreGlobal;
    }

    @Override
    public void putAll(Map<? extends String, ? extends RankingGlobal> m) {
        try (Connection con = DAOconfig.getConnection()) {
            for (Entry<? extends String, ? extends RankingGlobal> entry : m.entrySet()) {
                RankingGlobal scoreGlobal = entry.getValue();
                String sql = "INSERT INTO ranking_global (IdConta, Score) "
                        + "VALUES (?, ?) ON DUPLICATE KEY UPDATE "
                        + "IdConta = VALUES(IdConta), "
                        + "Score = VALUES(Score)";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, scoreGlobal.getIdConta());
                pstm.setInt(2, scoreGlobal.getScore());
                pstm.executeUpdate();
                pstm.close();
            }
        } catch (SQLException e) {
            // Erro ao inserir dados na scoreGlobal...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public RankingGlobal remove(Object key) {
        RankingGlobal scoreGlobal = this.get(key);
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM ranking_global WHERE IdConta = ?")) {
            stm.setString(1, (String) key);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Erro ao remover conta...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return scoreGlobal;
    }

    @Override
    public int size() {
        int size = 0;
        String sql = "SELECT COUNT(*) FROM ranking_global";
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            if (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return size;
    }

    @Override
    public Collection<RankingGlobal> values() {
        List<RankingGlobal> scoresGlobais = new ArrayList<>();
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM contas")) {
            while (rs.next()) {
                String id = rs.getString("IdConta");
                int score = rs.getShort("Score");
                RankingGlobal scoreGlobal = new RankingGlobal(id, score);
                scoresGlobais.add(scoreGlobal);
            }
        } catch (SQLException e) {
            // Erro ao selecionar contas...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return scoresGlobais;
    }

}