public class ADM {

    private int idADM;
    private String nome;
    private String CPF;
    private String senha;

    public ADM(int idADM, String nome, String CPF, String senha) {
        this.idADM = idADM;
        this.nome = nome;
        this.CPF = CPF;
        this.senha = senha;
    }

    // Getters e Setters (IA)
    public int getIdADM() {
        return idADM;
    }

    public void setIdADM(int idADM) {
        this.idADM = idADM;
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
