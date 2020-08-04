package ui;

import model.VocabularyList;
import model.VocabularyQuizList;
import persistence.VocabularyFileSystem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GraphicalUI {
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints constraints;
    private GraphicalUIAssist guiAssist;
    private VocabularyList vocabularyList;
    private VocabularyFileSystem fileTool;
    private VocabularyQuizList quiz;
    private String filename = "vocabularyData.txt";

    public GraphicalUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        vocabularyList = new VocabularyList();

        try {
            fileTool = new VocabularyFileSystem(vocabularyList, filename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"Fail to connect with your file system.",
                    "File System Error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            fileTool.load();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"Fail to load file data into your vocabulary list.",
                        "Loading Error", JOptionPane.ERROR_MESSAGE);
        }

        guiAssist = new GraphicalUIAssist(this, vocabularyList);
        quiz = new VocabularyQuizList(vocabularyList);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public GridBagConstraints getConstraints() {
        return constraints;
    }

    public VocabularyFileSystem getFileTool() {
        return fileTool;
    }

    // EFFECTS: set up basic frame format
    public void setUp() {
        constraints.insets = new Insets(10, 10, 10, 10);
        if (!(panel == null)) {
            frame.remove(panel);
        }
        panel = new JPanel(new GridBagLayout());
        frame.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: save vocabulary list to file
    public void save() {
        try {
            fileTool.flushFile();
            fileTool.save();
            JOptionPane.showMessageDialog(frame,
                    "Succeed to save your vocabulary list.");
        } catch (IOException err) {
            JOptionPane.showMessageDialog(frame,
                    "Fail to save your vocabulary list in a file.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: showing howe page
    public void home() {
        setUp();
        guiAssist.homeTitle();
        guiAssist.homeCommand();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing list page
    public void list() {
        setUp();
        guiAssist.listHead();
        JList list = guiAssist.listBody();
        guiAssist.listCommand(list);
        frame.setVisible(true);
    }

    // MODIFIES: this
    public void detail(int index) {
        setUp();
        guiAssist.detailHead();
        guiAssist.detailBody(index);
        guiAssist.detailCommand(index);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing add page
    public void add() {

    }

    // MODIFIES: this
    // EFFECTS: showing quiz page
    public void quiz() {

    }

    public void main() {
        home();
    }
}
