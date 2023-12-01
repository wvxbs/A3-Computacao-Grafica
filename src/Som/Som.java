package Som;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    
    private Clip clip;
    private boolean tocando=false;
    private String[] sons = new String[10];

    //geters e setters


    public boolean isTocando() {return tocando;}
    public void setTocando(boolean tocando) {this.tocando = tocando;}

    // construtores
    public Som() {
        sons[0] = "sons/faz-o-l-vinheta.wav";
    }

    // construtor pra passar as músicas
    public Som(String[] playlist) {
        // loop pra passar até 10 efeitos sonoros
        for (int i=0; (i<playlist.length && i <10); i++) {sons[i] = playlist[i];}
    }

    public void DefinirArquivo (int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(sons[i]));
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {
            e.getStackTrace();
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
