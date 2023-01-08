package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.AbstractMap;
import java.util.ArrayList;

import SimuladorLN.SSCampeonato.SSCorrida.Caracteristica;
import SimuladorLN.SSCampeonato.SSCorrida.Circuito;

public class CircuitoDAO implements Map<String, Circuito> {

    private static CircuitoDAO singleton = null;

    private CircuitoDAO() {
        try (Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuito (" +
                    "IdCircuito INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "NomeCircuito varchar(45) DEFAULT NULL," +
                    "Distancia float DEFAULT 0.0," +
                    "NCurvas int DEFAULT 0," +
                    "NChicanes int DEFAULT 0," +
                    "NRetas int DEFAULT 0)";
            stm.executeUpdate(sql);

            //insereCircuito();
            //insereCaracteristicas();

            sql = "CREATE TABLE IF NOT EXISTS caracteristicas ( " +
                    "IdCaracteristica INT NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                    "NomeCaracteristica VARCHAR(15) DEFAULT NULL, " +
                    "GDU FLOAT DEFAULT 0," +
                    "IdCircuito INT NOT NULL, " +
                    "FOREIGN KEY (IdCircuito) REFERENCES circuito(IdCircuito));";
            stm.executeUpdate(sql);

        } catch (SQLException e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    private void insereCircuito() {
        String sql = "INSERT INTO circuito (IdCircuito, NomeCircuito, Distancia, NCurvas, NChicanes, NRetas) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, 1);
            stm.setString(2, "");
            stm.setFloat(3, 5.5f);
            stm.setInt(4, 3);
            stm.setInt(5, 2);
            stm.setInt(6, 4);
            stm.executeUpdate();

            /*
            stm.setInt(1, 2);
            stm.setString(2, "B");
            stm.setFloat(3, 7.0f);
            stm.setInt(4, 4);
            stm.setInt(5, 3);
            stm.setInt(6, 3);
            stm.executeUpdate();
            */
        } catch (SQLException e) {
            // Erro ao inserir circuitos...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    private void insereCaracteristicas() {
        String sql = "INSERT INTO caracteristicas (IdCaracteristica, NomeCaracteristica, GDU, IdCircuito) VALUES (?, ?, ?, ?)";
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            Random rand = new Random();
            // Circuito 1
            stm.setInt(4, 1); // IdCircuito do Circuito 1
            for (int id = 0; id < 3 + 2 + 5; id++) {
                for (int i = 1; i <= 3; i++) {
                    stm.setInt(1, id);
                    stm.setString(2, "Curva");
                    stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
                    stm.executeUpdate();
                }
                for (int i = 1; i <= 2; i++) {
                    stm.setInt(1, id);
                    stm.setString(2, "Chicane");
                    stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
                    stm.executeUpdate();
                }
                for (int i = 1; i <= 5; i++) {
                    stm.setInt(1, id);
                    stm.setString(2, "Reta");
                    stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
                    stm.executeUpdate();
                }
            }
            /*
             * Insere caracteristicas para o Circuito 2
             * stm.setInt(4, 2); // IdCircuito do Circuito 2
             * for (int id = 0; id < 4 + 3 + 3; id++) {
             * for (int i = 1; i <= 4; i++) {
             * stm.setInt(1, id);
             * stm.setString(2, "Curva");
             * stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
             * stm.executeUpdate();
             * }
             * for (int i = 1; i <= 3; i++) {
             * stm.setInt(1, id);
             * stm.setString(2, "Chicane");
             * stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
             * stm.executeUpdate();
             * }
             * for (int i = 1; i <= 3; i++) {
             * stm.setInt(1, id);
             * stm.setString(2, "Reta");
             * stm.setInt(3, rand.nextInt(3) + 1); // GDU aleatório entre 1 e 3
             * stm.executeUpdate();
             * }
             */
            } catch (

        SQLException e) {
            // Erro ao inserir caracteristicas...
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
            String sql = "SELECT COUNT(*) AS size FROM circuito INNER JOIN caracteristicas ON circuito.IdCircuito = caracteristicas.IdCircuito";
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
                        && circuito.getnRetas() == c.getnRetas()
                        && circuito.getCaracteristicas().equals(c.getCaracteristicas())) {
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
                    Integer idCircuito = rs.getInt("IdCircuito");
                    String nomeCircuito = rs.getString("NomeCircuito");
                    float distancia = rs.getFloat("Distancia");
                    int nCurvas = rs.getInt("NCurvas");
                    int nChicanes = rs.getInt("NChicanes");
                    int nRetas = rs.getInt("NRetas");
                    circuito = new Circuito(idCircuito, nomeCircuito, distancia, nCurvas, nChicanes, nRetas);

                    String sql2 = "SELECT * FROM caracteristicas WHERE IdCircuito = ?";
                    PreparedStatement stm2 = con.prepareStatement(sql2);
                    stm2.setInt(1, idCircuito);
                    ResultSet rs2 = stm2.executeQuery();
                    while (rs2.next()) {
                        Integer idCaracteristica = rs2.getInt("IdCaracteristica");
                        String nomeCaracteristica = rs2.getString("NomeCaracteristica");
                        int GDU = rs2.getInt("GDU");
                        Caracteristica caracteristica = new Caracteristica(idCaracteristica, nomeCaracteristica, GDU);
                        circuito.addCaracteristica(caracteristica);
                    }
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
                        "DELETE FROM caracteristicas WHERE idCircuito = ?");
                PreparedStatement stm2 = con.prepareStatement(
                        "DELETE FROM circuito WHERE idCircuito = ?")) {
            for (String id : this.keySet()) {
                stm.setString(1, id);
                stm.executeUpdate();
                stm2.setString(1, id);
                stm2.executeUpdate();
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
                Integer idCircuito = rs.getInt("idCircuito");
                String nomeCircuito = rs.getString("nomeCircuito");
                float distancia = rs.getFloat("distancia");
                int nCurvas = rs.getInt("nCurvas");
                int nChicanes = rs.getInt("nChicanes");
                int nRetas = rs.getInt("nRetas");

                Circuito circuito = new Circuito(idCircuito, nomeCircuito, distancia, nCurvas, nChicanes, nRetas);
                entries.add(new AbstractMap.SimpleEntry<>(idCircuito.toString(), circuito));
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
                pstm.setInt(1, circuito.getIdCircuito());
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
        if (circuito != null) {
            try (
                    Connection con = DAOconfig.getConnection();
                    PreparedStatement stm = con.prepareStatement(
                            "DELETE FROM caracteristicas WHERE IdCircuito = ?")) {
                stm.setInt(1, circuito.getIdCircuito());
                stm.executeUpdate();

                PreparedStatement stm2 = con.prepareStatement(
                        "DELETE FROM circuito WHERE IdCircuito = ?");
                stm2.setString(1, (String) key);
                stm2.executeUpdate();
            } catch (SQLException e) {
                // Erro ao remover circuito...
                e.printStackTrace();
                throw new NullPointerException(e.getMessage());
            }
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
                Integer id = rs.getInt("idCircuito");
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