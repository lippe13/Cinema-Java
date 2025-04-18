import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Compra {
    private int clienteId;
    private int sessaoId;
    private int quantidadeIngressos;
    
    public Compra(int clienteId, int sessaoId, int quantidadeIngressos) {
        this.clienteId = clienteId;
        this.sessaoId = sessaoId;
        this.quantidadeIngressos = quantidadeIngressos;
    }

    // Método para verificar a capacidade disponível na sessão
    private int verificarCapacidade() {
        String query = "SELECT Capacidade FROM Cinema.Sessao WHERE idSessao = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, sessaoId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("Capacidade");
            }
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Erro");
        }
        return 0;
    }

    // Método para atualizar a capacidade da sessão
    private boolean atualizarCapacidade(int novaCapacidade) {
        String query = "UPDATE Cinema.Sessao SET Capacidade = ? WHERE idSessao = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, novaCapacidade);
            stmt.setInt(2, sessaoId);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Erro");
        }
        return false;
    }

    // Método para realizar a compra
    public boolean realizarCompra() {
        int capacidadeAtual = verificarCapacidade();
        if (capacidadeAtual >= quantidadeIngressos) {
            if (atualizarCapacidade(capacidadeAtual - quantidadeIngressos)) {
    
                String queryCompra = "INSERT INTO Cinema.Compra (CompraCliente, SessaoCompra, Quantidade) VALUES (?, ?, ?)";
                
                try (Connection conn = Conexao.getConexao();
                     PreparedStatement stmtCompra = conn.prepareStatement(queryCompra)) {
                    
                    stmtCompra.setInt(1, clienteId);
                    stmtCompra.setInt(2, sessaoId);
                    stmtCompra.setInt(3, quantidadeIngressos);
                    
                    stmtCompra.executeUpdate();
                    return true;
                } catch (SQLException e) {
                    System.out.println("");
                    System.out.println("Erro");
                }
            } else {
                System.out.println("");
                System.out.println("Erro");
            }
        } else {
            System.out.println("");
            System.out.println("NAO HA INGRESSOS DISPONIVEIS/SUFICIENTES");
        }
        return false;
    }
}
