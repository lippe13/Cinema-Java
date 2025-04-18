import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class DashboardADM {

    public static void exibirDashboard(ADM adm) {
        Scanner sc = new Scanner(System.in);
        int opcaoMenu = 0;

        while (opcaoMenu != 3) {
            System.out.println("");
            System.out.println("Dashboard ADM:");
            System.out.println("[1] ADICIONAR SESSAO");
            System.out.println("[2] COMPRAS REALIZADAS");
            System.out.println("[3] SAIR");
            System.out.print("Escolha uma opção: ");
            opcaoMenu = sc.nextInt();
            sc.nextLine();

            switch (opcaoMenu) {
                case 1: 
                    System.out.println("");
                    System.out.print("NOME DA SALA:");
                    String nomeSessao = sc.nextLine();

                    System.out.print("HORARIO E DIA (formato yyyy-MM-dd HH:mm): ");
                    String horarioSessao = sc.nextLine();

                    System.out.print("FILME: ");
                    String nomeFilme = sc.nextLine();

                    System.out.print("CINEMA: ");
                    String nomeCinema = sc.nextLine();

                    System.out.print("CAPACIDADE DA SALA: ");
                    int capacidadeSessao = sc.nextInt();
                    sc.nextLine();

                    Sessao sessao = new Sessao(nomeSessao, horarioSessao, nomeFilme, nomeCinema, capacidadeSessao);
                    if (sessao.adicionarSessao()) {
                        System.out.println("");
                        System.out.println("Sessão adicionada com sucesso!");
                    } else {
                        System.out.println("");
                        System.out.println("Erro ao adicionar a sessão.");
                    }

                    break;

                case 2: 

                    imprimirTodasCompras();
                    break;

                case 3:

                    break;

                default:
                    System.out.println("");
                    System.out.println("Opção invalida, tente novamente");
                    break;
            }
        }
    }

    public static void imprimirTodasCompras() {
        try (Connection conn = Conexao.getConexao()) {

            String sql = "SELECT C.CompraCliente, C.SessaoCompra, C.Quantidade, " +
                         "Cl.Nome AS ClienteNome, S.Sala, S.Horario, S.Filme " +
                         "FROM Cinema.Compra C " +
                         "JOIN Cinema.Sessao S ON C.SessaoCompra = S.idSessao " +
                         "JOIN Cinema.Cliente Cl ON C.CompraCliente = Cl.idCliente";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            System.out.println("COMPRAS REALIZADAS:");
            while (rs.next()) {
                int clienteId = rs.getInt("CompraCliente");
                int sessaoId = rs.getInt("SessaoCompra");
                int quantidade = rs.getInt("Quantidade");
                String clienteNome = rs.getString("ClienteNome");
                String sala = rs.getString("Sala");
                Timestamp horario = rs.getTimestamp("Horario");
                String filme = rs.getString("Filme");

                System.out.println("Cliente ID: " + clienteId + ", Nome: " + clienteNome +
                        ", Sessão ID: " + sessaoId + ", Filme: " + filme + ", Sala: " + sala +
                        ", Horário: " + horario + ", Quantidade de ingressos: " + quantidade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar compras: " + e.getMessage());
        }
    }
}
    

