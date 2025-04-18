import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/Cinema"; //DATABASE
    private static final String USER = "lipe"; // USER
    private static final String PASSWORD = "robin"; // SENHA

    public static Connection getConexao() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println("");
            System.err.println("Erro de conexão: " + e.getMessage());
            throw e;
        }
    }
}
