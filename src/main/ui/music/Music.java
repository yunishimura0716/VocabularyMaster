package ui.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public abstract class Music {
    protected Clip clip;
    protected AudioInputStream inputStream;

    public Music(String filename) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        String filepath = makePath(filename);
        clip = AudioSystem.getClip();
        inputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
        clip.open(inputStream);
    }

    // EFFECTS: create file path to get in data directory
    public String makePath(String filename) throws IOException {
        String dataDir = new File("data").getCanonicalPath();
        String filepath = dataDir + File.separator + filename;
        return filepath;
    }

    // EFFECTS: play ui.music
    public abstract void play() throws IOException;
}
