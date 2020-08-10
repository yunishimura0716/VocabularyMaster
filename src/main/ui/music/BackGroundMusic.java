package ui.music;

import javax.sound.sampled.*;

import java.io.IOException;

/*
class provide background ui.music
 */
public class BackGroundMusic extends Music {

    public BackGroundMusic(String filename) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        super(filename);
    }

    // EFFECTS: play ui.music continuously
    @Override
    public void play() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // EFFECTS: stop playing ui.music
    public void stop() {
        clip.stop();
    }
}
