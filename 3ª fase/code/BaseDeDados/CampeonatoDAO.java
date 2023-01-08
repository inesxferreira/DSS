package BaseDeDados;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.*;
import SimuladorLN.SSCampeonato.SSCorrida.Circuito;

public class CampeonatoDAO implements Map<String, Campeonato> {
    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS campeonato (" +
                    "idCampeonato INT PRIMARY KEY AUTO_INCREMENT, " +
                    "nome VARCHAR(30) NOT NULL);";
            stm.executeUpdate(sql);

            // sql = "INSERT INTO campeonato (nome) VALUES ('Campeonato de Teste');";
            // stm.executeUpdate(sql);

            //sql = "INSERT INTO circuito_campeonato (idCampeonato, idCircuito) VALUES (1, 1), (1, 2);";
            //stm.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS circuito_campeonato (" +
                    "idCampeonato INTEGER NOT NULL," +
                    "idCircuito INT NOT NULL," +
                    "PRIMARY KEY (idCampeonato, idCircuito)," +
                    "FOREIGN KEY (idCampeonato) REFERENCES campeonato(idCampeonato)," +
                    "FOREIGN KEY (idCircuito) REFERENCES circuito(idCircuito))";
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
    public static CampeonatoDAO getInstance() {
        if (CampeonatoDAO.singleton == null) {
            CampeonatoDAO.singleton = new CampeonatoDAO();
        }
        return CampeonatoDAO.singleton;
    }

    @Override
    public Campeonato put(String id, Campeonato campeonato) {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DAOconfig.getConnection();
            stm = con.prepareStatement(
                    "INSERT INTO campeonato (idCampeonato, nome) VALUES (?,?)");
            stm.setInt(1, Integer.valueOf(id));
            stm.setString(2, campeonato.getNome());
            stm.executeUpdate();
            for (Circuito circuito : campeonato.getCircuitos().values()) {
                stm = con.prepareStatement(
                        "INSERT INTO circuito_campeonato (idCampeonato, idCircuito) VALUES (?,?)");
                stm.setInt(1, Integer.valueOf(id));
                stm.setInt(2, circuito.getIdCircuito());
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            // Erro a inserir campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return campeonato;
    }

    @Override
    public Campeonato get(Object key) {
        String idCampeonato = (String) key;
        Campeonato campeonato = null;
        String sql = "SELECT * FROM campeonato WHERE idCampeonato = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCampeonato);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    campeonato = new Campeonato(idCampeonato, nome);
                }
            }
        } catch (SQLException e) {
            // Erro a obter campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }

        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            sql = "SELECT idCircuito FROM circuito_campeonato WHERE idCampeonato = '" + idCampeonato + "'";
            try (ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    String idCircuito = rs.getString("idCircuito");
                    Circuito circuito = CircuitoDAO.getInstance().get(idCircuito);
                    campeonato.getCircuitos().put(idCircuito, circuito);
                }
            }
        } catch (SQLException e) {
            // Erro a obter circuitos do campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonato;
    }

    @Override
    public boolean containsKey(Object key) {
        String idCampeonato = (String) key;
        boolean res = false;
        String sql = "SELECT * FROM Campeonato WHERE idCampeonato = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCampeonato);
            try (ResultSet rs = stm.executeQuery()) {
                res = rs.next();
            }
        } catch (SQLException e) {
            // Erro a verificar existência do campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return res;
    }

    @Override
    public Set<String> keySet() {
        Set<String> campeonatos = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT idCampeonato FROM campeonato");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                campeonatos.add(rs.getString("idCampeonato"));
            }
        } catch (SQLException e) {
            // Erro a obter campeonatos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonatos;
    }

    @Override
    public Campeonato remove(Object key) {
        String idCampeonato = (String) key;
        Campeonato campeonato = this.get(idCampeonato);
        if (campeonato == null) {
            return null;
        }
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM campeonato WHERE idCampeonato = ?")) {
            stm.setString(1, idCampeonato);
            stm.executeUpdate();
            PreparedStatement pstm = con.prepareStatement(
                    "DELETE FROM circuito_campeonato WHERE idCampeonato = ?");
            pstm.setString(1, idCampeonato);
            pstm.executeUpdate();
        } catch (SQLException e) {
            // Erro a remover campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonato;
    }

    @Override
    public int size() {
        int size = 0;
        String sql = "SELECT COUNT(*) FROM campeonato";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                size = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Erro a contar campeonatos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return size;
    }

    @Override
    public Collection<Campeonato> values() {
        Collection<Campeonato> campeonatos = new ArrayList<>();
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "SELECT * FROM campeonato";
            try (ResultSet rs = stm.executeQuery(sql)) {
                while (rs.next()) {
                    String idCampeonato = rs.getString("idCampeonato");
                    String nome = rs.getString("nome");
                    HashMap<String, Circuito> circuitos = (HashMap<String, Circuito>) getCircuitos(idCampeonato);
                    Campeonato campeonato = new Campeonato(idCampeonato, nome, circuitos);
                    campeonatos.add(campeonato);
                }
            }
        } catch (SQLException e) {
            // Erro a selecionar campeonatos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonatos;
    }

    private Map<String, Circuito> getCircuitos(String idCampeonato) {
        Map<String, Circuito> circuitos = new HashMap<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT idCircuito FROM circuito_campeonato WHERE idCampeonato = ?")) {
            stm.setString(1, idCampeonato);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String idCircuito = rs.getString("idCircuito");
                    Circuito circuito = CircuitoDAO.getInstance().get(idCircuito);
                    circuitos.put(idCircuito, circuito);
                }
            }
        } catch (SQLException e) {
            // Erro a selecionar circuitos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return circuitos;
    }

    @Override
    public void clear() {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM campeonato WHERE idCampeonato = ?")) {
            for (String id : this.keySet()) {
                stm.setString(1, id);
                stm.executeUpdate();
                PreparedStatement pstm = con.prepareStatement("DELETE FROM circuito_campeonato WHERE idCampeonato = ?");
                pstm.setString(1, id);
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            // Erro ao remover campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsValue(Object value) {
        Campeonato campeonato = (Campeonato) value;
        boolean resultado = false;
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT * FROM campeonato WHERE nome = ?")) {
            stm.setString(1, campeonato.getNome());
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    resultado = true;
                }
            }
        } catch (SQLException e) {
            // Erro a verificar se campeonato existe...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return resultado;
    }

    @Override
    public Set<Map.Entry<String, Campeonato>> entrySet() {
        Set<Map.Entry<String, Campeonato>> set = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT idCampeonato FROM campeonato")) {
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("idCampeonato");
                    Campeonato campeonato = this.get(id);
                    set.add(new AbstractMap.SimpleEntry<>(id, campeonato));
                }
            }
        } catch (SQLException e) {
            // Erro a selecionar campeonatos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        for (Map.Entry<? extends String, ? extends Campeonato> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}