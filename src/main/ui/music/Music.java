package ui.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public abstract class Music {
    protected Clip clip;
    protected AudioInputStream inputStream;
    protected String filepath;

    public Music(String filename) throws IOException {
        filepath = makePath(filename);
    }

    // EFFECTS: create file path to get in data directory
    public String makePath(String filename) throws IOException {
        String dataDir = new File("data").getCanonicalPath();
        String filepath = dataDir + File.separator + filename;
        return filepath;
    }

    // EFFECTS: open clip of audio system
    public void openClip() {
        try {
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip.open(inputStream);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: play music
    public abstract void play();
}
