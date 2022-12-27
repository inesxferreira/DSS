package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import SimuladorLN.SSCampeonato.SSCorrida.Circuito;

public class CircuitoDAO implements Map<String, Circuito> {

    private static CircuitoDAO singleton = null;

    private CircuitoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS circuitos (" +
                    "NomeCircuito varchar(45) NOT NULL PRIMARY KEY," +
                    "Distancia float DEFAULT 0.0," +
                    "NCurvas int DEFAULT 0," +
                    "NChicanes int DEFAULT 0," +
                    "NRetas int DEFAULT 0)";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            // Erro a criar tabela...
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
        int i = 0;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT count(*) FROM circuitos")) {
            if (rs.next()) {
                i = rs.getInt(1);
            }
        } catch (Exception e) {
            // Erro a criar tabela...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Id FROM circuitos WHERE Id='" + key.toString() + "'")) {
            r = rs.next();
        } catch (SQLException e) {
            // Database error!
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Circuito t = (Circuito) value;
        return this.containsKey(t.getNomeCircuito());
    }

@Override
    public Circuito get(Object key) {
        Circuito t = null;
        
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                 /**
                ResultSet rs = stm.executeQuery("SELECT * FROM circuito WHERE Id='" + key + "'")) {
            if (rs.next()) { // A chave existe na tabela
                // Reconstruir a colecção de alunos da turma
                Collection<String> alunos = getAlunosTurma(key.toString(), stm);

                // Reconstruir a Sala
                Sala s = null;
                String sql = "SELECT * FROM salas WHERE Num='" + rs.getString("Sala") + "'";
                try (ResultSet rsa = stm.executeQuery(sql)) {
                    if (rsa.next()) { // Encontrou a sala
                        s = new Sala(rs.getString("Sala"),
                                rsa.getString("Edificio"),
                                rsa.getInt("Capacidade"));
                    } else {
                        // BD inconsistente!! Sala não existe - tratar com excepções.
                    } // catch é feito no try inicial - este try serve para fechar o ResultSet
                      // automaticamente
                      // Nota: abrir um novo ResultSet no mesmo Statement fecha o ResultSet anterior
                }

                // Reconstruir a turma cokm os dados obtidos da BD
                t = new Turma(rs.getString("Id"), s, alunos);

            }
            */
        }catch(

    SQLException e)
    {
        // Database error!
        e.printStackTrace();
        throw new NullPointerException(e.getMessage());
    }return t;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<Entry<String, Circuito>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Circuito put(String key, Circuito value) {
        Circuito res = null;

        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD)) {
            Statement stm = conn.createStatement();

            String sql = "INSERT INTO circuito VALUES ('" + value.getNomeCircuito() + "', '" +
                    value.getDistancia() + "', '" +
                    value.getNCurvas() + "', '" +
                    value.getNChicanes() + "', '" +
                    value.getNRetas() + "')";
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            // Database error!
            ex.printStackTrace();
            throw new NullPointerException(ex.getMessage());
        }
        return res;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Circuito> m) {
        // TODO Auto-generated method stub

    }

    @Override
    public Circuito remove(Object key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<Circuito> values() {
        // TODO Auto-generated method stub
        return null;
    }
}