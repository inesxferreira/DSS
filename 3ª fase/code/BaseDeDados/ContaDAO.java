package BaseDeDados;

import SimuladorLN.SSConta.Conta;
import java.util.*;
import java.sql.*;

public class ContaDAO implements Map<String, Conta> {

    private static ContaDAO singleton = null;

    private ContaDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS conta(" +
                    "IdConta INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    "Username VARCHAR(15) NOT NULL," +
                    "Password VARCHAR(15) NOT NULL," +
                    "VersaoPremium BOOLEAN DEFAULT false)";
            stm.executeUpdate(sql);

            sql = "INSERT INTO conta (Username, Password, VersaoPremium) VALUES " +
                    "('robert', '123', 0), ('marta', '123', 0), ('ines', '123', 0), ('joana', '123', 0), ('rafael', '123', 0)";
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
    public static ContaDAO getInstance() {
        if (ContaDAO.singleton == null) {
            ContaDAO.singleton = new ContaDAO();
        }
        return ContaDAO.singleton;
    }

    @Override
    public void clear() {
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM conta WHERE IdConta = ?")) {
            for (String id : this.keySet()) {
                stm.setString(1, id);
                stm.executeUpdate();
            }
        } catch (SQLException e) {
            // Erro ao remover conta...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
    }

    @Override
    public boolean containsKey(Object key) {
        boolean containsKey = false;
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT IdConta FROM conta WHERE IdConta = ?")) {
            stm.setString(1, key.toString());
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    containsKey = true;
                }
            }
        } catch (SQLException e) {
            // Erro ao verificar se existe conta com o id especificado...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return containsKey;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof Conta) {
            Conta conta = (Conta) value;
            try (Connection conn = DAOconfig.getConnection();
                    PreparedStatement stm = conn.prepareStatement(
                            "SELECT * FROM conta WHERE IdConta = ? AND Password = ?")) {
                stm.setInt(1, conta.getIdConta());
                stm.setString(2, conta.getPassword());
                try (ResultSet rs = stm.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro a verificar existência da conta na base de dados", e);
            }
        }
        return false;
    }

    @Override
    public Set<Map.Entry<String, Conta>> entrySet() {
        Set<Map.Entry<String, Conta>> entries = new HashSet<>();
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "SELECT * FROM conta");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                Integer idConta = rs.getInt("IdConta");
                String user = rs.getString("Username");
                String pass = rs.getString("Password");
                boolean versao = rs.getBoolean("VersaoPremium");

                Conta conta = new Conta(idConta, user, pass, versao);
                entries.add(new AbstractMap.SimpleEntry<>(idConta.toString(), conta));
            }
        } catch (SQLException e) {
            // Erro ao selecionar contas...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return entries;
    }

    @Override
    public Conta get(Object key) {
        Conta conta = null;
        String sql = "SELECT * FROM conta WHERE IdConta = ?";

        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, (String) key);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Integer idConta = rs.getInt("IdConta");
                    String user = rs.getString("Username");
                    String pass = rs.getString("Password");
                    boolean versao = rs.getBoolean("VersaoPremium");
                    conta = new Conta(idConta, user, pass, versao);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
        }
        return conta;
    }

    @Override
    public boolean isEmpty() {
        String sql = "SELECT COUNT(*) FROM conta";
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
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        String sql = "SELECT IdConta FROM conta";
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(sql);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                set.add(rs.getString("IdConta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public Conta put(String id, Conta conta) {
        try (Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO conta (IdConta,UserName,Password,VersaoPremium) VALUES (?,?,?,?)")) {
            stm.setString(1, id);
            stm.setString(2, conta.getUsername());
            stm.setString(3, conta.getPassword());
            stm.setBoolean(4, conta.getVersaoPremium());
            stm.executeUpdate();

        } catch (SQLException e) {
            // Erro a inserir conta...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return conta;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Conta> m) {
        try (Connection con = DAOconfig.getConnection()) {
            for (Entry<? extends String, ? extends Conta> entry : m.entrySet()) {
                Conta conta = entry.getValue();
                String sql = "INSERT INTO conta (IdConta,Username, Password, VersaoPremium) "
                        + "VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE "
                        + "IdConta = VALUES(IdConta), "
                        + "Username = VALUES(Username),"
                        + "Password = VALUES(Password), "
                        + "VersaoPremium = VALUES(VersaoPremium)";
                PreparedStatement pstm = con.prepareStatement(sql);
                pstm.setInt(1, conta.getIdConta());
                pstm.setString(2, conta.getUsername());
                pstm.setString(2, conta.getPassword());
                pstm.setBoolean(3, conta.getVersaoPremium());
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
    public Conta remove(Object key) {
        Conta conta = this.get(key);
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "DELETE FROM conta WHERE IdConta = ?")) {
            stm.setString(1, (String) key);
            stm.executeUpdate();
        } catch (SQLException e) {
            // Erro ao remover conta...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return conta;
    }

    @Override
    public int size() {
        int size = 0;
        String sql = "SELECT COUNT(*) FROM conta";
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
    public Collection<Conta> values() {
        List<Conta> contas = new ArrayList<>();
        try (
                Connection con = DAOconfig.getConnection();
                Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM conta")) {
            while (rs.next()) {
                Integer id = rs.getInt("IdConta");
                String user = rs.getString("Username");
                String password = rs.getString("Password");
                boolean versao = rs.getBoolean("VersaoPremium");
                Conta conta = new Conta(id, user, password, versao);
                contas.add(conta);
            }
        } catch (SQLException e) {
            // Erro ao selecionar contas...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return contas;
    }

}