package BaseDeDados;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.SSCarro.*;

public class CarroDAO implements Map<String, Carro> {

    private static CarroDAO singleton = null;

    private CarroDAO() {
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS carro (" +
                    "IdCarro varchar(10) NOT NULL PRIMARY KEY," +
                    "Categoria varchar(15) DEFAULT NULL," +
                    "Marca varchar(15) DEFAULT NULL," +
                    "Modelo varchar(15) DEFAULT NULL," +
                    "Pac float(15) DEFAULT 0," +
                    "Fiabilidade float(15) DEFAULT NULL," +
                    "TipoPneus int(4) DEFAULT 0)," +
                    "ModoMotor int(4) DEFAULT 0)," +
                    "Cililndrada int(4) DEFAULT 0)," +
                    "Potencia int(4) DEFAULT 0)," +
                    "PotenciaC int(4) DEFAULT 0)";

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
                        "INSERT INTO carro (IdCarro, Categoria, Marca, Modelo, Pac, Fiabilidade, TipoPneus, ModoMotor, Cilindrada, Potencia, PotenciaC) VALUES (?,?,?,?,?,?,?,?,?,?,?)")) {
            stm.setString(1, id);
            stm.setString(2, carro.getCategoria());
            stm.setString(3, carro.getMarca());
            stm.setString(4, carro.getModelo());
            stm.setFloat(5, carro.getPac());
            stm.setFloat(6, carro.getFiabilidade());
            stm.setInt(7, carro.getTipoPneus());
            stm.setInt(8, carro.getModoMotor());
            stm.setInt(9, carro.getCilindrada());
            stm.setInt(10, carro.getPotencia());
            stm.setInt(11, carro.getPotenciaC());
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
        String idCarro = (String) key;
        Carro carro = null;
        String sql = "SELECT * FROM carro WHERE IdCarro = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCarro);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String categoria = rs.getString("Categoria");
                    String marca = rs.getString("Marca");
                    String modelo = rs.getString("Modelo");
                    float pac = rs.getFloat("Pac");
                    float fiabilidade = rs.getFloat("Fiabilidade");
                    int tipoPneus = rs.getInt("TipoPneus");
                    int modoMotor = rs.getInt("ModoMotor");
                    int cilindrada = rs.getInt("Cilindrada");
                    int potencia = rs.getInt("Potencia");
                    int potenciaC = rs.getInt("PotenciaC");
                    switch (categoria) {
                        case "C1":
                            carro = new C1(idCarro, modelo, marca, categoria, pac, tipoPneus, modoMotor, potencia,
                                    potenciaC, cilindrada);
                            break;
                        case "C2":
                            carro = new C2(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                    potencia, potenciaC, cilindrada);
                            break;
                        case "GT":
                            carro = new GT(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                    potencia, potenciaC, cilindrada, 10.0f);
                            break;
                        case "SC":
                            carro = new SC(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                    potencia, potenciaC, cilindrada);
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
                String idCarro = rs.getString("IdCarro");
                String categoria = rs.getString("Categoria");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                float pac = rs.getFloat("Pac");
                float fiabilidade = rs.getFloat("Fiabilidade");
                int tipoPneus = rs.getInt("TipoPneus");
                int modoMotor = rs.getInt("ModoMotor");
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("Potencia");
                int potenciaC = rs.getInt("PotenciaC");
                switch (categoria) {
                    case "C1":
                        carro = new C1(idCarro, modelo, marca, categoria, pac, tipoPneus, modoMotor, potencia,
                                potenciaC, cilindrada);
                        break;
                    case "C2":
                        carro = new C2(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada);
                        break;
                    case "GT":
                        carro = new GT(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada, 10.0f);
                        break;
                    case "SC":
                        carro = new SC(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada);
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
            stm.setString(1, carro.getIdCarro());
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
                String idCarro = rs.getString("IdCarro");
                String categoria = rs.getString("Categoria");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                float pac = rs.getFloat("Pac");
                float fiabilidade = rs.getFloat("Fiabilidade");
                int tipoPneus = rs.getInt("TipoPneus");
                int modoMotor = rs.getInt("ModoMotor");
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("Potencia");
                int potenciaC = rs.getInt("PotenciaC");
                Carro carro = null;
                switch (categoria) {
                    case "C1":
                        carro = new C1(idCarro, modelo, marca, categoria, pac, tipoPneus, modoMotor, potencia,
                                potenciaC, cilindrada);
                        break;
                    case "C2":
                        carro = new C2(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada);
                        break;
                    case "GT":
                        carro = new GT(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada, 10.0f);
                        break;
                    case "SC":
                        carro = new SC(idCarro, modelo, marca, categoria, pac, fiabilidade, tipoPneus, modoMotor,
                                potencia, potenciaC, cilindrada);
                        break;
                }
                set.add(new AbstractMap.SimpleEntry<>(idCarro, carro));
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