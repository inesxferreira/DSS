package BaseDeDados;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.SSCarro.Piloto;

public class PilotoDAO implements Map<String, Piloto> {

    private static PilotoDAO singleton = null;

    private PilotoDAO() {
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS piloto (" +
                    "IdPiloto varchar(10) NOT NULL PRIMARY KEY," +
                    "Nome varchar(15) DEFAULT NULL," +
                    "CTS float(15) DEFAULT 0.0," +
                    "SVA float(15) DEFAULT 0.0)";

            stm.executeUpdate(sql);

            if (con != null)
                con.close();

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
    public static PilotoDAO getInstance() {
        if (PilotoDAO.singleton == null) {
            PilotoDAO.singleton = new PilotoDAO();
        }
        return PilotoDAO.singleton;
    }

    @Override
    public Piloto put(String id, Piloto piloto) {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO piloto (IdPiloto, Nome, CTS, SVA) VALUES (?,?,?,?)")) {
            stm.setString(1, id);
            stm.setString(2, piloto.getNome());
            stm.setFloat(3, piloto.getCTS());
            stm.setFloat(4, piloto.getSVA());
            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a inserir carro...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return piloto;
    }

    @Override
    public Piloto get(Object key) {
        String idPiloto = (String) key;
        Piloto piloto = null;
        String sql = "SELECT * FROM piloto WHERE IdPiloto = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idPiloto);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    Float cts = rs.getFloat("CTS");
                    Float sva = rs.getFloat("SVA");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return piloto;
    }

    @Override
    public boolean containsKey(Object key) {
        String idPiloto = (String) key;
        boolean res = false;
        String sql = "SELECT * FROM piloto WHERE IdPiloto = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idPiloto);
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
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        String sql = "SELECT IdPiloto FROM piloto";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                set.add(rs.getString("IdPiloto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public Piloto remove(Object key) {
        String idPiloto = (String) key;
        Piloto piloto = get(idPiloto);
        String sql = "DELETE FROM piloto WHERE IdPiloto = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idPiloto);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return piloto;
    }

    @Override
    public int size() {
        int size = 0;
        String sql = "SELECT COUNT(*) FROM piloto";

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
    public Collection<Piloto> values() {
        Collection<Piloto> values = new ArrayList<>();
        Piloto piloto = null;
        String sql = "SELECT * FROM piloto WHERE IdPiloto = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String idPiloto = rs.getString("IdPiloto");
                String nome = rs.getString("Nome");
                Float cts = rs.getFloat("CTS");
                Float sva = rs.getFloat("SVA");

                values.add(piloto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM piloto";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof Piloto))
            return false;
        Piloto piloto = (Piloto) value;

        String sql = "SELECT * FROM piloto WHERE IdPiloto = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, piloto.getIdPiloto());
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
    public Set<Entry<String, Piloto>> entrySet() {
        Set<Map.Entry<String, Piloto>> set = new HashSet<>();
        String sql = "SELECT * FROM piloto";

        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                String idPiloto = rs.getString("IdPiloto");
                String nome = rs.getString("Nome");
                Float cts = rs.getFloat("CTS");
                Float sva = rs.getFloat("SVA");

                Piloto piloto = null;

                set.add(new AbstractMap.SimpleEntry<>(idPiloto, piloto));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM piloto";
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
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        for (Map.Entry<? extends String, ? extends Piloto> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}