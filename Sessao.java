import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sessao {
    private String sala;
    private String horario;
    private String filme;
    private String cinema;
    private int capacidade;

    // Construtor
    public Sessao(String sala, String horario, String filme, String cinema, int capacidade) {
        this.sala = sala;
        this.horario = horario;
        this.filme = filme;
        this.cinema = cinema;
        this.capacidade = capacidade;
    }

    // Método para adicionar a sessão no banco de dados
    public boolean adicionarSessao() {
        String query = "INSERT INTO Cinema.Sessao (Sala, Horario, Filme, Cinema, Capacidade) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, sala);
            stmt.setString(2, horario);
            stmt.setString(3, filme);
            stmt.setString(4, cinema);
            stmt.setInt(5, capacidade);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("");
            System.out.println("Erro ao adicionar sessão: " + e.getMessage());
            return false;
        }
    }

    // Getters e Setters (IA)
    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
