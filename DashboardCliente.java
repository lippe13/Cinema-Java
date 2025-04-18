import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

public class DashboardCliente {
    public static void exibirDashboardCliente(Cliente cliente) {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 4) {
        
            System.out.println("");
            System.out.println("DASHBOARD CLIENTE");
            System.out.println("[1] SUAS COMPRAS");
            System.out.println("[2] COMPRAR INGRESSO");
            System.out.println("[3] SESSOES DISPONIVEIS");
            System.out.println("[4] VOLTAR AO MENU");

            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1:
                    // Exibir compras do cliente
                    System.out.println("");
                    listarCompras(cliente);
                    System.out.println("");
                    break;

                case 2:
                    // Comprar ingresso
                    System.out.println("");
                    System.out.println("ID da Sessão que que deseja comprar: ");
                    int sessaoId = sc.nextInt();
                    System.out.print("Digite a quantidade de ingressos: ");
                    int quantidadeIngressos = sc.nextInt();
                    
                    Compra compra = new Compra(cliente.getIdCliente(), sessaoId, quantidadeIngressos);
                    if (compra.realizarCompra()) {
                        System.out.println("");
                        System.out.println("Compra realizada com sucesso!");
                    } else {
                        System.out.println("");
                        System.out.println("Erro");
                    }
                    break;

                case 3:
                    System.out.println("");
                    System.err.println("Sessoes Disponiveis:");
                    imprimirTodasSessao();
                case 4:
                    break;

                default:
                System.out.println("");
                System.out.println("Opção invalida, tente novamente");
            }
        }
    }

    // Método para listar as compras feitas pelo cliente
    private static void listarCompras(Cliente cliente) {
        String query = "SELECT * FROM Cinema.Compra WHERE CompraCliente = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, cliente.getIdCliente());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("");
                System.out.println("Sessão ID: " + rs.getInt("SessaoCompra"));
                System.out.println("Quantidade de ingressos: " + rs.getInt("Quantidade"));
            }
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Erro" + e.getMessage());
        }
    }

    // Método para exibir todas as sessões disponíveis
    public static void imprimirTodasSessao() {
        try (Connection conn = Conexao.getConexao()) {
            String sql = "SELECT * FROM Cinema.Sessao";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("");

            System.out.println("Sessões Disponíveis:");
            while (rs.next()) {
                int idSessao = rs.getInt("idSessao");
                String sala = rs.getString("Sala");
                Timestamp horario = rs.getTimestamp("Horario");
                String filme = rs.getString("Filme");
                String cinema = rs.getString("Cinema");
                int capacidade = rs.getInt("Capacidade");

                // Exibe as informações de cada sessão
                System.out.println("ID: " + idSessao + ", Sala: " + sala + ", Filme: " + filme +
                        ", Cinema: " + cinema + ", Horário: " + horario + ", Capacidade: " + capacidade);
            }
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Erro" + e.getMessage());
        }
    }
}
