package BaseDeDados;

import java.sql.*;
import java.util.*;
import SimuladorLN.SSCampeonato.SSCarro.*;

public class CarroDAO implements Map<String, Carro> {

    private static CarroDAO singleton = null;

    private CarroDAO() {
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carro (" +
                    "IdCarro INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Categoria VARCHAR(2) DEFAULT NULL," +
                    "Marca VARCHAR(15) DEFAULT NULL," +
                    "Modelo VARCHAR(15) DEFAULT NULL," +
                    "Cilindrada int(4) DEFAULT 0," +
                    "PotenciaC int(4) DEFAULT 0)";

            stm.executeUpdate(sql);
            /*
             * sql = "INSERT INTO carro (Categoria, Marca, Modelo, Cilindrada, PotenciaC)" +
             * "VALUES ('C1', 'Mercedes', 'AMG GT3', 6000, 710)," +
             * "('C1', 'Porsche', '911 GT3 R', 6000, 690)," +
             * "('C2', 'Audi', 'R8 LMS GT3', 4800, 630)," +
             * "('C2', 'McLaren', '720S GT3', 4200, 600)," +
             * "('GT', 'Ford', 'Mustang GT', 3000, 500)," +
             * "('GT', 'Nissan', 'GT-R Nismo GT3', 3600, 550)," +
             * "('SC', 'Toyota', 'Supra', 2500, 400);";
             */
            // stm.executeUpdate(sql);
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
    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
    }

    @Override
    public Carro put(String id, Carro carro) {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO carro (IdCarro, Categoria, Marca, Modelo, Cilindrada, PotenciaC) VALUES (?,?,?,?,?,?)")) {
            stm.setInt(1, Integer.valueOf(id));
            stm.setString(2, carro.getCategoria());
            stm.setString(3, carro.getMarca());
            stm.setString(4, carro.getModelo());
            stm.setInt(5, carro.getCilindrada());
            stm.setInt(6, carro.getPotenciaC());
            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a inserir carro...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return carro;
    }

    @Override
    public Carro get(Object key) {
        Integer idCarro = Integer.valueOf((String) key);
        Carro carro = null;
        String sql = "SELECT * FROM carro WHERE IdCarro = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, idCarro);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String categoria = rs.getString("Categoria");
                    String marca = rs.getString("Marca");
                    String modelo = rs.getString("Modelo");
                    int cilindrada = rs.getInt("Cilindrada");
                    int potencia = rs.getInt("PotenciaC");
                    switch (categoria) {
                        case "C1":
                            carro = new C1(idCarro, marca, modelo, categoria, potencia, cilindrada);
                            break;
                        case "C2":
                            carro = new C2(idCarro, marca, modelo, categoria, potencia, cilindrada);
                            break;
                        case "GT":
                            carro = new GT(idCarro, marca, modelo, categoria, potencia, cilindrada);
                            break;
                        case "SC":
                            carro = new SC(idCarro, marca, modelo, categoria, potencia, cilindrada);
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return carro;
    }

    @Override
    public boolean containsKey(Object key) {
        String idCarro = (String) key;
        boolean res = false;
        String sql = "SELECT * FROM carro WHERE IdCarro = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCarro);
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
        String sql = "SELECT IdCarro FROM carro";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                set.add(rs.getString("IdCarro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public Carro remove(Object key) {
        String idCarro = (String) key;
        Carro carro = get(idCarro);
        String sql = "DELETE FROM carro WHERE IdCarro = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCarro);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    @Override
    public int size() {
        int size = 0;
        String sql = "SELECT COUNT(*) FROM carro";

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
    public Collection<Carro> values() {
        Collection<Carro> values = new ArrayList<>();
        Carro carro = null;
        String sql = "SELECT * FROM carro WHERE IdCarro = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Integer idCarro = rs.getInt("IdCarro");
                String categoria = rs.getString("Categoria");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("PotenciaC");
                switch (categoria) {
                    case "C1":
                        carro = new C1(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "C2":
                        carro = new C2(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "GT":
                        carro = new GT(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "SC":
                        carro = new SC(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                }
                values.add(carro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }

    @Override
    public void clear() {
        String sql = "DELETE FROM carro";
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
        if (!(value instanceof Carro))
            return false;
        Carro carro = (Carro) value;

        String sql = "SELECT * FROM carro WHERE IdCarro = ?";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setInt(1, carro.getIdCarro());
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
    public Set<Entry<String, Carro>> entrySet() {
        Set<Map.Entry<String, Carro>> set = new HashSet<>();
        String sql = "SELECT * FROM carro";

        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Integer idCarro = rs.getInt("IdCarro");
                String categoria = rs.getString("Categoria");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("PotenciaC");
                Carro carro = null;
                switch (categoria) {
                    case "C1":
                        carro = new C1(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "C2":
                        carro = new C2(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "GT":
                        carro = new GT(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                    case "SC":
                        carro = new SC(idCarro, marca, modelo, categoria, potencia, cilindrada);
                        break;
                }
                set.add(new AbstractMap.SimpleEntry<>(idCarro.toString(), carro));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM carro";
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
    public void putAll(Map<? extends String, ? extends Carro> m) {
        for (Map.Entry<? extends String, ? extends Carro> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }
}