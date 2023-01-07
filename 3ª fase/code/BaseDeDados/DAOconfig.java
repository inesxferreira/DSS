package BaseDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOconfig {
    static final String USERNAME = "pausa";
    static final String PASSWORD = "agora";
    private static final String DATABASE = "formula1";
    private static final String DRIVER = "jdbc:mysql";
    static final String URL = DRIVER + "://localhost:3306/" + DATABASE;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            //throw new RuntimeException("Erro ao obter conexão com a base de dados", e);
            e.printStackTrace();
        }
        return null;

    }
}
