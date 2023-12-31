package Model;

import java.io.File;

public class EfeitoSonoro {
    private String Nome;
    private String Caminho;
    private File Arquivo;

    public EfeitoSonoro(String nome, String caminho, File arquivo) {
        Nome = nome;
        Caminho = caminho;
        Arquivo = arquivo;
    }

    public EfeitoSonoro(String nome, String caminho) {
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

    public File getArquivo() {
        return Arquivo;
    }

    public void setArquivo(File arquivo) {
        Arquivo = arquivo;
    }
}
