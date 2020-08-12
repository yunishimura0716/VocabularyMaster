package ui;

import exceptions.NotEnoughVocabularyException;
import exceptions.VocabularyListBoundsException;
import model.Vocabulary;
import model.VocabularyList;
import model.VocabularyQuizList;
import persistence.VocabularyFileSystem;
import ui.assist.GraphicalUIAssist;
import ui.assist.QuizRunnerGraphicalUI;
import ui.image.ImagePanel;
import ui.music.BackGroundMusic;
import ui.music.ButtonEventMusic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


/*
Class provide graphical user interface
 */
public class GraphicalUI extends UserInterface {
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints constraints;
    private GraphicalUIAssist guiAssist;
    private BackGroundMusic bgm;
    public ButtonEventMusic buttonEffect;
    private String bgiPath;


    public GraphicalUI() throws IOException {
        super();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;

        guiAssist = new GraphicalUIAssist(this, vocabularyList);

        try {
            bgm = new BackGroundMusic("bgm_2.wav");
            bgiPath = makePath("background_image5.jpg");
            buttonEffect = new ButtonEventMusic("button_event3.wav");
        } catch (Throwable e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame,"Fail to connect with your file system.",
                    "File System Error", JOptionPane.ERROR_MESSAGE);
        }
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

    public GraphicalUIAssist getGuiAssist() {
        return guiAssist;
    }

    public BackGroundMusic getBgm() {
        return bgm;
    }

    public ButtonEventMusic getButtonEffect() {
        return buttonEffect;
    }

    public String makePath(String filename) throws IOException {
        return bgm.makePath(filename);
    }

    // EFFECTS: set up basic frame format
    public void setUp() {
        constraints.insets = new Insets(10, 10, 10, 10);
        if (!(panel == null)) {
            frame.remove(panel);
        }
        panel = new ImagePanel(new GridBagLayout(), bgiPath);
        frame.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: save vocabulary list to file
    @Override
    public void save() {
        int n = JOptionPane.showConfirmDialog(frame,
                "If you save this list, you substitute this for current file data.\n"
                        + "Would you like to save your vocabulary?",
                "Confirm Message",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            try {
                fileTool.flushFile();
                fileTool.save();
                JOptionPane.showMessageDialog(frame,
                        "Succeed to save your vocabulary list.");
            } catch (IOException err) {
                err.printStackTrace();
                JOptionPane.showMessageDialog(frame,
                        "Fail to save your vocabulary list in a file.",
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (VocabularyListBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: load file data to vocabulary list
    public void load() {
        int n = JOptionPane.showConfirmDialog(frame,
                "If you load the file data, you substitute it for current list.\n"
                        + "Would you like to load file data?",
                "Confirm Message",
                JOptionPane.YES_NO_OPTION);
        if (n == 0) {
            try {
                fileTool.load();
                JOptionPane.showMessageDialog(frame,
                        "Succeed to load your vocabulary data.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(frame, "Fail to load file data into your vocabulary list.",
                        "Loading Error", JOptionPane.ERROR_MESSAGE);
            } catch (VocabularyListBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: showing howe page
    @Override
    public void home() {
        setUp();
        guiAssist.homeTitle();
        guiAssist.homeCommand();
        frame.setVisible(true);
        bgm.play();
    }

    // MODIFIES: this
    // EFFECTS: showing list page
    @Override
    public void list() {
        setUp();
        guiAssist.listHead();
        JList list = null;
        try {
            list = guiAssist.listBody();
        } catch (VocabularyListBoundsException e) {
            e.printStackTrace();
        }
        guiAssist.listCommand(list);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing detail page
    @Override
    public void detail(int index) {
        setUp();
        guiAssist.detailHead();
        try {
            guiAssist.detailBody(index);
            guiAssist.detailCommand(index);
        } catch (VocabularyListBoundsException e) {
            e.printStackTrace();
        }

        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing add page
    @Override
    public void add() {
        setUp();
        guiAssist.addHead();
        ArrayList<JTextField> textFields = guiAssist.addBody();
        guiAssist.addCommand(textFields);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing quiz page
    @Override
    public void quiz() {
        try {
            quiz.makeQuizList();
            QuizRunnerGraphicalUI quizRunner = new QuizRunnerGraphicalUI(this);
            setUp();;
            quizRunner.instruction();
            frame.setVisible(true);
        } catch (NotEnoughVocabularyException e) {
            JOptionPane.showMessageDialog(frame,
                    "At least 10 unremembered vocabularies are needed.",
                    "Quiz Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (VocabularyListBoundsException e) {
            e.printStackTrace();
        }
    }

    public void main() {
        home();
    }
}
