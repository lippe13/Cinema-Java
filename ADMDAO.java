import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ADMDAO {

    // Cadastrar um novo ADM
    public static boolean cadastrarADM(ADM adm) {
        String sql = "INSERT INTO Cinema.ADM (Nome, CPF, Senha) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, adm.getNome());
            ps.setString(2, adm.getCPF());
            ps.setString(3, adm.getSenha());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            System.out.println("");
            System.err.println("Erro ao inserir dados/User existente");
        }
        return false;
    }

    // Logar um ADM
    public static ADM logarADM(String CPF, String senha) {
        String sql = "SELECT * FROM Cinema.ADM WHERE CPF = ? AND Senha = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, CPF);
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ADM(
                    rs.getInt("idADM"),
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
