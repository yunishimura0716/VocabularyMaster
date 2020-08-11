package ui;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        ConsoleUI myApp = new ConsoleUI();
        GraphicalUI myApp = null;
        try {
            myApp = new GraphicalUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myApp.main();
    }
}
