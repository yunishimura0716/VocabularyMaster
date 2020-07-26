package model;

import java.util.ArrayList;

/*
class provide the list of vocabulary quiz
 */
public class VocabularyQuizList {
    private ArrayList<VocabularyQuiz> vocabularyQuizList;
    private VocabularyList vocabularyList;
    public static final int MAX_NUMBER_QUIZ = 10;

    public VocabularyQuizList(VocabularyList vocabularyList) {
        this.vocabularyList = vocabularyList;
    }

    // MODIFIES: this
    // EFFECTS: make lis of vocabulary quiz
    public void makeQuizList() {
        // stub
    }

    // EFFECTS: return a vocabulary quiz object
    public VocabularyQuiz viewQuiz(int index) {
        return null;
    }
}
