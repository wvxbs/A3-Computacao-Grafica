package Som;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    
    private Clip clip;
    private String[] sons = new String[10];

    public Som() {
        sons[0] = "sons/faz-o-l-vinheta.wav";
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
    }

    public void Loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void Pausar() {
        clip.stop();
        clip.close();
    }
}
