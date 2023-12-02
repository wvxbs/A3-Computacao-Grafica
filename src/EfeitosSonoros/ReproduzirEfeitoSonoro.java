package EfeitosSonoros;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class ReproduzirEfeitoSonoro {
    
    private Clip clip;
    private boolean tocando=false;
    private ArrayList<Som> sons;
    private Som arquivoSelecionado;
    public void setTocando(boolean tocando) {this.tocando = tocando;}

    // /construtores
    public ReproduzirEfeitoSonoro() {

        GerarListaDeEfeitosSonoros listaDeEfeitosSonoros = new GerarListaDeEfeitosSonoros();
        listaDeEfeitosSonoros.ExibirListaDeEfeitosSonoros();
        sons = listaDeEfeitosSonoros.getEfeitosSonoros();
    }

    //geters e setters
    public boolean isTocando() {return tocando;}
    public String getNomeDoArquivoSelecionado() {
        return arquivoSelecionado.getNome();
    }

    public void DefinirArquivo (String nome){
        String NomeAnalisado = nome.toLowerCase();
        NomeAnalisado = nome.concat(".wav");
        for (Som som : sons) {
            if (NomeAnalisado.equals(som.getNome())) {
                arquivoSelecionado = som;
                try {
                    AudioInputStream ais = AudioSystem.getAudioInputStream(arquivoSelecionado.getArquivo());
                    clip = AudioSystem.getClip();
                    clip.open(ais);
                } catch(Exception e) {
                    e.getStackTrace();
                    System.out.println("----------> erro na definição de arquivo de audio <----------");
                }
            }
        }
    }

    public void Reproduzir() {
        clip.start();
        tocando = true;
    }

    public void Loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void Pausar() {
        clip.stop();
        clip.close();
        tocando = false;
    }
}
