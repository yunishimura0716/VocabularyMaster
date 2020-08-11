package ui.music;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

/*
class provide background ui.music
 */
public class BackGroundMusic extends Music {

    public BackGroundMusic(String filename) throws IOException {
        super(filename);
        openClip();
    }

    // EFFECTS: play music continuously
    @Override
    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // EFFECTS: stop playing music
    public void stop() {
        clip.stop();
    }
}
