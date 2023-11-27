package Som;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Som (){
        soundURL[0] = getClass().getResource("/sons/faz-o-l-vinheta");
    }

    private void SetFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e) {}
    }

    public void Play() {
        clip.start();
    }

    public void Loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void Stop() {
        clip.stop();
    }
}
