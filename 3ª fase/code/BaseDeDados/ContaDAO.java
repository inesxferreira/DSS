package BaseDeDados;

import SimuladorLN.SSConta.Conta;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.sql.*;
import static java.util.stream.Collectors.*;

public class ContaDAO implements Map<String, Conta> {

    private static ContaDAO singleton = null;

    private ContaDAO() {
        try (Connection conn = DAOconfig.getConnection();
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS contas(" +
                    "IdConta varchar(15) NOT NULL PRIMARY KEY," +
                    "Password varchar(15) DEFAULT 0)";
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
    public static ContaDAO getInstance() {
        if (ContaDAO.singleton == null) {
            ContaDAO.singleton = new ContaDAO();
        }
        return ContaDAO.singleton;
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
    public Set<Entry<String, Conta>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Conta get(Object key) {
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
    public Conta put(String arg0, Conta arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Conta> m) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Conta remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Conta> values() {
        // TODO Auto-generated method stub
        return null;
    }

}