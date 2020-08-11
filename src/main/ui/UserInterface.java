package ui;

import model.Vocabulary;
import model.VocabularyList;
import model.VocabularyQuizList;
import persistence.VocabularyFileSystem;

import java.io.IOException;

public abstract class UserInterface {
    protected VocabularyList vocabularyList;
    protected VocabularyFileSystem fileTool;
    protected VocabularyQuizList quiz;
    protected String filename = "vocabularyData.txt";

    public UserInterface() throws IOException {
        vocabularyList = new VocabularyList();
        quiz = new VocabularyQuizList(vocabularyList);
        fileTool = new VocabularyFileSystem(vocabularyList, filename);
    }

    public VocabularyQuizList getQuiz() {
        return quiz;
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

    // MODiFIES: this
    // EFFECTS: save vocabulary list into file
    public abstract void save();

    // MODIFIES: this
    // EFFECTS: showing home page
    public abstract void home();

    // MODIFIES: this
    // EFFECTS: showing list page
    public abstract void list();

    // MODIFIES: this
    // EFFECTS: showing adding page
    public abstract void add();

    // MODIFIES: this
    // EFFECTS: showing detail page
    public abstract void detail(int vocabNum);

    // MODIFIES: this
    // EFFECTS: showing quiz page
    public abstract void quiz();
}
