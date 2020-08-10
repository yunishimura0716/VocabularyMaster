package ui.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/*
Class provide sound effect when push button
 */
public class ButtonEventMusic {
    private String filepath;
    private Clip clip;
    private AudioInputStream inputStream;

    public ButtonEventMusic(String filename) throws IOException {
        filepath = makePath(filename);
    }

    // EFFECTS: create file path to get in data directory
    public String makePath(String filename) throws IOException {
        String dataDir = new File("data").getCanonicalPath();
        String filepath = dataDir + File.separator + filename;
        return filepath;
    }

    // EFFECTS: play sound effect
    public void play() {
        try {
            clip = AudioSystem.getClip();
            inputStream = AudioSystem.getAudioInputStream(new File(filepath).getAbsoluteFile());
            clip.open(inputStream);
            clip.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
