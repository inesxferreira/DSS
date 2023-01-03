package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.SSCarro.Carro;
import SimuladorLN.SSCampeonato.SSCarro.GT;

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
    public Carro get(Object chave) {
        Carro carro = new GT();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement("SELECT * FROM carro WHERE idCarro=?")) {
            stm.setString(1, (String) chave);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    carro.setIdCarro(rs.getString("IdCarro"));
                    carro.setCategoria(rs.getString("Categoria"));
                    carro.setMarca(rs.getString("Marca"));
                    carro.setModelo(rs.getString("Modelo"));
                    carro.setPac(rs.getFloat("Pac"));
                    carro.setFiabilidade(rs.getFloat("Fiabilidade"));
                    carro.setPneus(rs.getInt("TipoPneus"));
                    carro.setModoMotor(rs.getInt("ModoMotor"));
                    carro.setCilindrada(rs.getInt("Cilindrada"));
                    carro.setPotencia(rs.getInt("Potencia"), 0);
                    carro.setPotenciaC(rs.getInt("PotenciaC"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carro;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<Entry<String, Carro>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Carro> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public Carro remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Carro> values() {
        // TODO Auto-generated method stub
        return null;
    }
}