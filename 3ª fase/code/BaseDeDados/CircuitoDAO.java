package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.AbstractMap;
import java.util.ArrayList;

import SimuladorLN.SSCampeonato.SSCorrida.Circuito;

public class CircuitoDAO implements Map<String, Circuito> {

    private static CircuitoDAO singleton = null;

    private CircuitoDAO() {
        try (Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuito (" +
                    "IdCircuito varchar(45) NOT NULL PRIMARY KEY," +
                    "NomeCircuito varchar(45) DEFAULT NULL," +
                    "Distancia float DEFAULT 0.0," +
                    "NCurvas int DEFAULT 0," +
                    "NChicanes int DEFAULT 0," +
                    "NRetas int DEFAULT 0)";
            stm.executeUpdate(sql);

            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    public static CircuitoDAO getInstance() {
        if (CircuitoDAO.singleton == null) {
            CircuitoDAO.singleton = new CircuitoDAO();
        }
        return CircuitoDAO.singleton;
    }

    @Override
    public int size() {
        int size = 0;
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "SELECT COUNT(*) AS size FROM circuito";
            try (ResultSet rs = stm.executeQuery(sql)) {
                if (rs.next())
                    size = rs.getInt("size");
            }
        } catch (SQLException e) {
            // Erro ao contar elementos da tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean containsKey = false;
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT idCircuito FROM circuito WHERE idCircuito = ?")) {
            stm.setString(1, key.toString());
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    containsKey = true;
                }
            }
        } catch (SQLException e) {
            // Erro ao verificar se existe circuito com o id especificado...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return containsKey;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof Circuito) {
            Circuito circuito = (Circuito) value;
            for (Circuito c : this.values()) {
                if (circuito.getIdCircuito().equals(c.getIdCircuito())
                        && circuito.getNomeCircuito().equals(c.getNomeCircuito())
                        && circuito.getDistancia() == c.getDistancia()
                        && circuito.getnCurvas() == c.getnCurvas()
                        && circuito.getnChicanes() == c.getnChicanes()
                        && circuito.getnRetas() == c.getnRetas()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Circuito get(Object key) {
        Circuito circuito = null;
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                    "SELECT * FROM circuito WHERE IdCircuito = ?")) {
            stm.setString(1, (String) key);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String idCircuito = rs.getString("IdCircuito");
                    String nomeCircuito = rs.getString("NomeCircuito");
                    float distancia = rs.getFloat("Distancia");
                    int nCurvas = rs.getInt("NCurvas");
                    int nChicanes = rs.getInt("NChicanes");
                    int nRetas = rs.getInt("NRetas");
                    circuito = new Circuito(idCircuito, nomeCircuito, distancia, nCurvas, nChicanes, nRetas);
                }
            }
        } catch (SQLException e) {
            // Erro ao selecionar circuito...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuito;
    }

    @Override
    public void clear() {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                    "DELETE FROM circuito WHERE idCircuito = ?")) {
            for (String id : this.keySet()) {
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            // Erro ao remover circuito...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<Map.Entry<String, Circuito>> entrySet() {
        Set<Map.Entry<String, Circuito>> entries = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT * FROM circuito");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String idCircuito = rs.getString("idCircuito");
                String nomeCircuito = rs.getString("nomeCircuito");
                float distancia = rs.getFloat("distancia");
                int nCurvas = rs.getInt("nCurvas");
                int nChicanes = rs.getInt("nChicanes");
                int nRetas = rs.getInt("nRetas");

                Circuito circuito = new Circuito(idCircuito, nomeCircuito, distancia, nCurvas, nChicanes, nRetas);
                entries.add(new AbstractMap.SimpleEntry<>(idCircuito, circuito));
            }
        } catch (SQLException e) {
            // Erro ao selecionar circuitos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return entries;
    }

    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        String sql = "SELECT IdCircuito FROM circuito";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                set.add(rs.getString("IdCircuito"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public Circuito put(String id, Circuito circuito) {
        Circuito circuitoAntigo = get(id);
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO circuito (IdCircuito, NomeCircuito, Distancia, NCurvas, NChicanes, NRetas) VALUES (?,?,?,?,?,?) ON DUPLICATE KEY UPDATE NomeCircuito=VALUES(NomeCircuito), Distancia=VALUES(Distancia), NCurvas=VALUES(NCurvas), NChicanes=VALUES(NChicanes), NRetas=VALUES(NRetas)")) {
            stm.setString(1, id);
            stm.setString(2, circuito.getNomeCircuito());
            stm.setFloat(3, circuito.getDistancia());
            stm.setInt(4, circuito.getnCurvas());
            stm.setInt(5, circuito.getnChicanes());
            stm.setInt(6, circuito.getnRetas());
            stm.executeUpdate();
        } catch (SQLException e) {
            // Erro ao inserir circuito...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuitoAntigo;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        try (Connection con = DAOconfig.getConnection()) {
            for (Entry<? extends String, ? extends Circuito> entry : m.entrySet()) {
                Circuito circuito = entry.getValue();
                String sql = "INSERT INTO circuito (IdCircuito, NomeCircuito, Distancia, NCurvas, NChicanes, NRetas) "
                        + "VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE "
                        + "NomeCircuito = VALUES(NomeCircuito), "
                        + "Distancia = VALUES(Distancia), "
                        + "NCurvas = VALUES(NCurvas), "
                        + "NChicanes = VALUES(NChicanes), "
                        + "NRetas = VALUES(NRetas)";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setString(1, circuito.getIdCircuito());
                pstm.setString(2, circuito.getNomeCircuito());
                pstm.setFloat(3, circuito.getDistancia());
                pstm.setInt(4, circuito.getnCurvas());
                pstm.setInt(5, circuito.getnChicanes());
                pstm.setInt(6, circuito.getnRetas());
                pstm.executeUpdate();
                pstm.close();
            }
        } catch (SQLException e) {
            // Erro ao inserir dados na tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Circuito remove(Object key) {
        Circuito circuito = this.get(key);
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM circuito WHERE idCircuito = ?")) {
            stm.setString(1, (String) key);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Erro ao remover circuito...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuito;
    }

    @Override
    public Collection<Circuito> values() {
        List<Circuito> circuitos = new ArrayList<>();
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM circuito")) {
            while (rs.next()) {
                String id = rs.getString("idCircuito");
                String nome = rs.getString("nomeCircuito");
                float distancia = rs.getFloat("distancia");
                int nCurvas = rs.getInt("nCurvas");
                int nChicanes = rs.getInt("nChicanes");
                int nRetas = rs.getInt("nRetas");
                Circuito circuito = new Circuito(id, nome, distancia, nCurvas, nChicanes, nRetas);
                circuitos.add(circuito);
            }
        } catch (SQLException e) {
            // Erro ao selecionar circuitos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuitos;
    }
}