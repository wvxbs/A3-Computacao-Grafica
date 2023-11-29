package Som;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    
    private Clip clip;
    // private URL[] soundURL = new URL[10];

    public Som (){
        File soundURL = new File("/sons/faz-o-l-vinheta.wav");
        Clip _clip;

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            _clip = AudioSystem.getClip();
            _clip.open(ais);
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
