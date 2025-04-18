public class Cliente {

    private int idCliente;
    private String nome;
    private String CPF;
    private String senha;

    public Cliente(int idCliente, String nome, String CPF, String senha) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.CPF = CPF;
        this.senha = senha;
    }

    // Getters e Setters (IA)
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
