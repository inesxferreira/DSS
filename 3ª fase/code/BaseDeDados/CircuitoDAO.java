package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

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
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuitos")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Id FROM circuitos WHERE Id='" + key.toString() + "'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Circuito t = (Circuito) value;
        return this.containsKey(t.getNomeCircuito());
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
                    int nChicanes = rs.getInt("NCurvas");
                    int nRetas = rs.getInt("NCurvas");

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
        // TODO Auto-generated method stub

    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub

    }

    @Override
    public Circuito remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Circuito> values() {
        // TODO Auto-generated method stub
        return null;
    }
}