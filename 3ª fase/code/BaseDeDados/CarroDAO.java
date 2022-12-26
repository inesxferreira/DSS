package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import SimuladorLN.SSCampeonato.SSCarro.Carro;

public class CarroDAO implements Map<String,Carro>{

    private static CarroDAO singleton = null;

    private CarroDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
             Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS salas (" +
                    "Num varchar(10) NOT NULL PRIMARY KEY," +
                    "Edificio varchar(45) DEFAULT NULL," +
                    "Capacidade int(4) DEFAULT 0)";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS turmas (" +
                    "Id varchar(10) NOT NULL PRIMARY KEY," +
                    "Sala varchar(10) DEFAULT NULL," +
                    "foreign key(Sala) references salas(Num))";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS alunos (" +
                    "Num varchar(10) NOT NULL PRIMARY KEY," +
                    "Nome varchar(45) DEFAULT NULL," +
                    "Email varchar(45) DEFAULT NULL," +
                    "Turma varchar(10), foreign key(Turma) references turmas(Id))";
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
    public static CarroDAO getInstance() {
        if (CarroDAO.singleton == null) {
            CarroDAO.singleton = new CarroDAO();
        }
        return CarroDAO.singleton;
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
    public Carro get(Object key) {
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
    public Carro put(String arg0, Carro arg1) {
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