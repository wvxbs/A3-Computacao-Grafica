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
//        sons[0] = "sons/faz-o-l-vinheta.wav";
//        sons[1] = "sons/blizzard-elimination.wav";
//        sons[2] = "sons/brazil.wav";
//        sons[3] = "sons/coin_1.wav";
//        sons[4] = "sons/keep-on.wav";
//        sons[5] = "sons/health.wav";
//        sons[6] = "sons/ihu-bolsonaro.wav";
//        sons[7] = "sons/jet-set-radio-cancel.wav";
//        sons[8] = "sons/jet-set-radio-success.wav";
//        sons[9] = "sons/faz-o-l-vinheta.wav";
//        sons[10] = "sons/the-doom-slayer.wav";
//        sons[11] = "sons/web_whatsapp.wav";
//        sons[12] = "sons/whatsapp.wav";

        GerarListaDeEfeitosSonoros listaDeEfeitosSonoros = new GerarListaDeEfeitosSonoros();
        listaDeEfeitosSonoros.ExibirListaDeEfeitosSonoros();
        sons = listaDeEfeitosSonoros.getEfeitosSonoros();
    }

    // construtor pra passar as músicas
//    public ReproduzirEfeitoSonoro(String[] playlist) {
//        // loop pra passar até 10 efeitos sonoros
//        for (int i=0; (i<playlist.length && i <10); i++) {sons[i] = playlist[i];}
//    }

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
