package BaseDeDados;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import SimuladorLN.SSCampeonato.*;

public class CampeonatoDAO implements Map<String, Campeonato> {
    private static CampeonatoDAO singleton = null;

    private CampeonatoDAO() {
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS contas(" +
                    "Nome varchar(45) NOT NULL PRIMARY KEY," +
                    "Score int(10) DEFAULT 0," +
                    "" +
                    "versaoPremium boolean DEFAULT false)";

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
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean containsKey(Object key) {
        boolean r;
        try (Connection conn = DriverManager.getConnection(DAOconfig.URL, DAOconfig.USERNAME, DAOconfig.PASSWORD);
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT Id FROM Campeonatos WHERE Id='" + key.toString() + "'")) {
            r = rs.next();
            {
                // falta
            }

        } catch (SQLException e) {
            // erro no campeonato
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Campeonato t = (Campeonato) value;
        return false;// this.containsKey(t.get());
    }

    @Override
    public Set<Entry<String, Campeonato>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campeonato get(Object key) {

        return null;
    }

    @Override
    public boolean isEmpty() {

        return this.size() == 0;
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Campeonato put(String arg0, Campeonato arg1) {
        try (
                Connection con = DAOconfig.getConnection();
                PreparedStatement stm = con.prepareStatement(
                        "INSERT INTO campeonato ()")) {

            stm.executeUpdate();
            if (con != null)
                con.close();

        } catch (SQLException e) {
            // Erro no campeonato...
            e.printStackTrace();
            throw new NullPointerException(e.getMessage());
        }
        return campeonato;

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