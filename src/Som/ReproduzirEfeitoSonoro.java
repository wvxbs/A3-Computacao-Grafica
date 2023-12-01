package Som;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ReproduzirEfeitoSonoro {
    
    private Clip clip;
    private boolean tocando=false;
    private String[] sons = new String[50];

    //geters e setters


    public boolean isTocando() {return tocando;}
    public void setTocando(boolean tocando) {this.tocando = tocando;}

    // construtores
    public ReproduzirEfeitoSonoro() {
        sons[0] = "sons/faz-o-l-vinheta.wav";
        sons[1] = "sons/blizzard-elimination.wav";
        sons[2] = "sons/brazil.wav";
        sons[3] = "sons/coin_1.wav";
        sons[4] = "sons/keep-on.wav";
        sons[5] = "sons/health.wav";
        sons[6] = "sons/ihu-bolsonaro.wav";
        sons[7] = "sons/jet-set-radio-cancel.wav";
        sons[8] = "sons/jet-set-radio-success.wav";
        sons[9] = "sons/faz-o-l-vinheta.wav";
        sons[10] = "sons/the-doom-slayer.wav";
        sons[11] = "sons/web_whatsapp.wav";
        sons[12] = "sons/whatsapp.wav";
    }

    // construtor pra passar as músicas
    public ReproduzirEfeitoSonoro(String[] playlist) {
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
            System.out.println("----------> erro na definição de arquivo de audio <----------");
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
