package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.*;
import SimuladorLN.SSCampeonato.SSCorrida.Circuito;

public class CampeonatoDAO implements Map<String, Campeonato> {
    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE Campeonato (" +
                    "idCampeonato INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR(255) NOT NULL);";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE circuito_campeonato (" +
                    "idCampeonato INTEGER NOT NULL," +
                    "idCircuito INTEGER NOT NULL," +
                    "PRIMARY KEY (idCampeonato, idCircuito)," +
                    "FOREIGN KEY (idCampeonato) REFERENCES Campeonato(idCampeonato)," +
                    "FOREIGN KEY (idCircuito) REFERENCES Circuito(idCircuito));";
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
    public Campeonato put(String idCampeonato, String nome) {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO Campeonato (idCampeonato, ) VALUES (?,?)")) {
            stm.setString(1, Campeonato.getCampeonato());
            stm.setString(2, Campeonato.getNome());
            stm.executeUpdate();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            // Erro a inserir campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return;
    }

    @Override
    public Campeonato put(String idCampeonato, String idCircuito) {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO circuito_campeonato(idCircuito, idCampeonato) VALUES (?,?)")) {
            stm.setString(1, Campeonato.getCampeonato());
            stm.setString(2, Circuito.getIdCircuito());
            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro a inserir carro...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return;
    }

    @Override
    public Campeonato get(Object key) {
        String idCampeonato = (String) key;
        Campeonato carro = null;
        String sql = "SELECT * FROM campeonato WHERE IdCampeonato = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, idCampeonato);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String categoria = rs.getString("Categoria");
                    String marca = rs.getString("Marca");
                    String modelo = rs.getString("Modelo");
                    int cilindrada = rs.getInt("Cilindrada");
                    int potencia = rs.getInt("Potencia");
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
                String idCarro = rs.getString("IdCarro");
                String categoria = rs.getString("Categoria");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("Potencia");
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
                int cilindrada = rs.getInt("Cilindrada");
                int potencia = rs.getInt("Potencia");
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

    @Override
    public Campeonato put(String arg0, Campeonato arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        // TODO Auto-generated method stub

    }
}