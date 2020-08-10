package ui;

import exceptions.NotEnoughVocabularyException;
import model.Vocabulary;
import model.VocabularyList;
import model.VocabularyQuizList;
import persistence.VocabularyFileSystem;
import ui.assist.GraphicalUIAssist;
import ui.assist.QuizRunnerGraphicalUI;
import ui.image.ImagePanel;
import ui.music.BackGroundMusic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


/*
Class provide graphical user interface
 */
public class GraphicalUI {
    private JFrame frame;
    private JPanel panel;
    private GridBagConstraints constraints;
    private GraphicalUIAssist guiAssist;
    private VocabularyList vocabularyList;
    private VocabularyFileSystem fileTool;
    private VocabularyQuizList quiz;
    private String filename = "vocabularyData.txt";
    private BackGroundMusic bgm;
    private String bgiPath;


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
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame,"Fail to connect with your file system.",
                    "File System Error", JOptionPane.ERROR_MESSAGE);
        }

        guiAssist = new GraphicalUIAssist(this, vocabularyList);
        quiz = new VocabularyQuizList(vocabularyList);

        try {
            bgm = new BackGroundMusic("bgm_2.wav");
            bgiPath = makePath("background_image5.jpg");
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

    public VocabularyQuizList getQuiz() {
        return quiz;
    }

    public GraphicalUIAssist getGuiAssist() {
        return guiAssist;
    }

    public BackGroundMusic getBgm() {
        return bgm;
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
    public void save() {
        try {
            fileTool.save();
            JOptionPane.showMessageDialog(frame,
                    "Succeed to save your vocabulary list.");
        } catch (IOException err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(frame,
                    "Fail to save your vocabulary list in a file.",
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: load file data to vocabulary list
    public void load() {
        try {
            if (vocabularyList.size() > 0) {
                throw new IOException();
            }
            fileTool.load();
            fileTool.flushFile();
            JOptionPane.showMessageDialog(frame,
                    "Succeed to load your vocabulary data.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame,"Fail to load file data into your vocabulary list.",
                    "Loading Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: add vocabularies into list
    public boolean post(String vocabs, String meanings) {
        String[] vocablist = {vocabs};
        String[] meaninglist = {meanings};
        if (vocabs.contains(",")) {
            vocablist = vocabs.split("\\s*,\\s*");
        }
        if (meanings.contains(",")) {
            meaninglist = meanings.split("\\s*,\\s*");
        }
        if (vocablist.length == meaninglist.length) {
            for (int i = 0; i < vocablist.length; i++) {
                Vocabulary vocabulary = new Vocabulary(vocablist[i], meaninglist[i]);
                vocabularyList.add(vocabulary);
            }
            return true;
        } else {
            return false;
        }

    }

    // MODIFIES: this
    // EFFECTS: showing howe page
    public void home() {
        setUp();
        guiAssist.homeTitle();
        guiAssist.homeCommand();
        frame.setVisible(true);
        bgm.play();
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
        setUp();
        guiAssist.addHead();
        ArrayList<JTextField> textFields = guiAssist.addBody();
        guiAssist.addCommand(textFields);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: showing quiz page
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
        }
    }

    public void main() {
        home();
    }
}
