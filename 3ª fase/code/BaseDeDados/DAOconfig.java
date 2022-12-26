package BaseDeDados;

public class DAOconfig {
    static final String USERNAME = "root";                       // Actualizar
    static final String PASSWORD = "rbs0284a";                       // Actualizar
    private static final String DATABASE = "formula1";          // Actualizar
    //private static final String DRIVER = "jdbc:mariadb";        // Usar para MariaDB
    private static final String DRIVER = "jdbc:mysql";        // Usar para MySQL
    static final String URL = DRIVER+"://localhost:3306/"+DATABASE;
}
