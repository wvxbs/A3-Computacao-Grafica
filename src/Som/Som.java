package Som;

public class Som {
    private String Nome;
    private String Caminho;

    public Som(String nome, String caminho) {
        Nome = nome;
        Caminho = caminho;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCaminho() {
        return Caminho;
    }

    public void setCaminho(String caminho) {
        Caminho = caminho;
    }
}
