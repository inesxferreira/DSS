package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
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
        String sql = "SELECT COUNT(*) FROM circuito";

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
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM circuito";
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
    public boolean containsKey(Object key) {
        String idCircuito = (String) key;
        boolean res = false;
        String sql = "SELECT * FROM circuito WHERE IdCircuito = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCircuito);
            try (ResultSet rs = stm.executeQuery()) {
                res = rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof Circuito))
            return false;
        Circuito circuito = (Circuito) value;

        String sql = "SELECT * FROM circuito WHERE IdCircuito = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, circuito.getIdCircuito());
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return false;
    }

    @Override
    public Circuito get(Object key) {
        String idCircuito = (String) key;
        Circuito circuito = null;
        String sql = "SELECT * FROM circuito WHERE IDCircuito = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCircuito);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String nomeCircuito = rs.getString("NomeCircuito");
                    Float distancia = rs.getFloat("Distancia");
                    int nCurvas = rs.getInt("NCurvas");
                    int nChicanes = rs.getInt("NChicanes");
                    int nRetas = rs.getInt("NRetas");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return circuito;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM circuito";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        Set<Map.Entry<String, Circuito>> set = new HashSet<>();
        String sql = "SELECT * FROM circuito";

        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                String idCircuito = rs.getString("IdCircuito");
                float distancia = rs.getFloat("Distancia");
                int nCurvas = rs.getInt("NCurvas");
                int nChicanes = rs.getInt("NChicanes");
                int nRetas = rs.getInt("NRetas");
                Circuito circuito = null;

                set.add(new AbstractMap.SimpleEntry<>(idCircuito, circuito));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
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
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO circuito (idCircuito, nomeCircuito, distancia, nCurvas, nChicanes, nRetas) VALUES (?,?,?,?,?,?)")) {
            stm.setString(1, id);
            stm.setString(2, circuito.getNomeCircuito());
            stm.setFloat(3, circuito.getDistancia());
            stm.setInt(4, circuito.getNCurvas());
            stm.setInt(5, circuito.getNChicanes());
            stm.setInt(6, circuito.getNRetas());
            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a inserir circuito...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuito;

    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        for (Map.Entry<? extends String, ? extends Circuito> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Circuito remove(Object key) {
        String idCircuito = (String) key;
        Circuito circuito = get(idCircuito);
        String sql = "DELETE FROM circuito WHERE IdCircuito = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCircuito);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return circuito;
    }

    @Override
    public Collection<Circuito> values() {
        Collection<Circuito> values = new ArrayList<>();
        Circuito circuito = null;
        String sql = "SELECT * FROM circuito WHERE IdCircuito = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String idCircuito = rs.getString("IdCircuito");
                Float distancia = rs.getFloat("Distancia");
                int nCurvas = rs.getInt("NCurvas");
                int nChicanes = rs.getInt("NChicanes");
                int nRetas = rs.getInt("NRetas");

                values.add(circuito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }
}