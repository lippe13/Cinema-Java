import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {

    // Cadastrar um novo Cliente
    public static boolean cadastrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cinema.Cliente (Nome, CPF, Senha) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCPF());
            ps.setString(3, cliente.getSenha());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("");
            System.err.println("Erro ao inserir dados/User existente");
        }
        return false;
    }

    // Logar um Cliente
    public static Cliente logarCliente(String CPF, String senha) {
        String sql = "SELECT * FROM Cinema.Cliente WHERE CPF = ? AND Senha = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, CPF);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("idCliente"),
                    rs.getString("Nome"),
                    rs.getString("CPF"),
                    rs.getString("Senha")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}
