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
                    "IdPiloto INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Nome varchar(20) DEFAULT NULL," +
                    "CTS float(15) DEFAULT 0.0," +
                    "SVA float(15) DEFAULT 0.0)";
            stm.executeUpdate(sql);
            sql = "INSERT INTO piloto (Nome, CTS, SVA) " +
                    "VALUES ('Lewis Hamilton', 0.93, 0.78), " +
                    "('Max Verstappen', 0.35, 0.72), " +
                    "('Charles Leclerc', 0.72, 0.34), " +
                    "('Sebastian Vettel', 0.84, 0.62), " +
                    "('Valtteri Bottas', 0.76, 0.72);";
            //stm.executeUpdate(sql);
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
            stm.setInt(1, Integer.valueOf(id));
            stm.setString(2, piloto.getNome());
            stm.setFloat(3, piloto.getCTS());
            stm.setFloat(4, piloto.getSVA());
            stm.executeUpdate();

        } catch (SQLException e) {
            // Erro a inserir carro...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return piloto;
    }

    @Override
    public Piloto get(Object key) {
        Integer idPiloto = Integer.valueOf((String) key);
        Piloto piloto = null;
        String sql = "SELECT * FROM piloto WHERE IdPiloto = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idPiloto);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("Nome");
                    Float cts = rs.getFloat("CTS");
                    Float sva = rs.getFloat("SVA");
                    piloto = new Piloto(idPiloto, nome, cts, sva);
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
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT IdPiloto FROM piloto WHERE IdPiloto = ?")) {
            stm.setString(1, (String) key);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            // Erro ao verificar se chave existe...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT IdPiloto FROM piloto")) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String idPiloto = rs.getString("IdPiloto");
                    keys.add(idPiloto);
                }
            }
        } catch (SQLException e) {
            // Erro a obter chaves...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return keys;
    }

    @Override
    public Piloto remove(Object key) {
        Piloto p = null;
        String id = (String) key;
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement("DELETE FROM piloto WHERE IdPiloto = ?")) {
            stm.setString(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return p;
    }

    @Override
    public int size() {
        try (Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "SELECT COUNT(*) FROM piloto";
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar o número de registros na tabela Piloto", e);
        }
        return 0;
    }

    @Override
    public Collection<Piloto> values() {
        Collection<Piloto> values = new ArrayList<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT * FROM piloto");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Piloto p = new Piloto();
                p.setIdPiloto(rs.getInt("IdPiloto"));
                p.setNome(rs.getString("Nome"));
                p.setCTS(rs.getFloat("CTS"));
                p.setSVA(rs.getFloat("SVA"));
                values.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return values;
    }

    @Override
    public void clear() {
        try (Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "DELETE FROM piloto";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
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
            stm.setInt(1, piloto.getIdPiloto());
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
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM piloto")) {

            while (rs.next()) {
                Piloto p = new Piloto();
                p.setIdPiloto(rs.getInt("IdPiloto"));
                p.setNome(rs.getString("Nome"));
                p.setCTS(rs.getFloat("CTS"));
                p.setSVA(rs.getFloat("SVA"));

                set.add(new AbstractMap.SimpleEntry<>(p.getIdPiloto().toString(), p));
            }

            if (con != null)
                con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        try (Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "SELECT COUNT(*) FROM piloto";
            ResultSet rs = stm.executeQuery(sql);
            return rs.getInt(1) == 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar se a tabela está vazia", e);
        }
    }

    @Override
    public void putAll(Map<? extends String, ? extends Piloto> m) {
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con
                        .prepareStatement("INSERT INTO piloto (IdPiloto, Nome, CTS, SVA) VALUES (?,?,?,?)")) {
            for (Map.Entry<? extends String, ? extends Piloto> entry : m.entrySet()) {
                stm.setString(1, entry.getKey());
                stm.setString(2, entry.getValue().getNome());
                stm.setFloat(3, entry.getValue().getCTS());
                stm.setFloat(4, entry.getValue().getSVA());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }
}