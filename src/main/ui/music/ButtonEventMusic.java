package ui.music;

import java.io.IOException;

/*
Class provide sound effect when push button
 */
public class ButtonEventMusic extends Music {

    public ButtonEventMusic(String filename) throws IOException {
        super(filename);
    }

    // EFFECTS: play music at once
    @Override
    public void play() {
        openClip();
        clip.start();
    }
}
