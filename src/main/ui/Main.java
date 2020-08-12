package ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ConsoleUI cuiApp = null;
        GraphicalUI guiApp = null;
        try {
            // cuiApp = new ConsoleUI();
            guiApp = new GraphicalUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        guiApp.main();
    }
}
