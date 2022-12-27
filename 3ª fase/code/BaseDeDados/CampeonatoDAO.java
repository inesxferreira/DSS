package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import SimuladorLN.SSCampeonato.*;

public class CampeonatoDAO implements Map<String, Campeonato> {
    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS contas(" +
                    "Nome varchar(45) NOT NULL PRIMARY KEY," +
                    "Score int(10) DEFAULT 0," +
                    "versaoPremium boolean DEFAULT false)";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS ranking_global (" +
                    "Id varchar(10) NOT NULL PRIMARY KEY," +
                    "Conta varchar(10) DEFAULT NULL," +
                    "foreign key(Conta) references contas(Identificador))";
            stm.executeUpdate(sql);
            sql = "CREATE TABLE IF NOT EXISTS participantes (" +
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
    public Set<Entry<String, Campeonato>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campeonato get(Object key) {
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
    public Campeonato put(String arg0, Campeonato arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Campeonato> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public Campeonato remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<Campeonato> values() {
        // TODO Auto-generated method stub
        return null;
    }
}