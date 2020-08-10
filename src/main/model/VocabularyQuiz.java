package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/*
class provide a set of vocabulary quiz
 */
public class VocabularyQuiz {
    private VocabularyList vocabularyList;
    public static final int NUM_SELECT = 4;
    private int answer = -1; // answer : [0, 1, 2, 3]
    private Vocabulary vocabulary;
    private ArrayList<String> selections;

    public VocabularyQuiz(VocabularyList vocabularyList) {
        this.vocabularyList = vocabularyList;
        selections = new ArrayList<>();
    }

    // REQUIRES: vocabularyNum >= 0
    // MODIFIES: this
    public void makeQuiz(int vocabularyNum) {
        setAnswer((int)(Math.random() * NUM_SELECT));
        setVocabulary(vocabularyList.view(vocabularyNum));
        ArrayList<Integer> indexes = makeIndexes();
        indexes.remove(vocabularyNum);

        makeDummy(0, getAnswer(), indexes);

        getSelections().add(vocabularyList.view(vocabularyNum).getMeaning());

        makeDummy(getAnswer() + 1, NUM_SELECT, indexes);
    }

    // EFFECTS: return value of answer
    public int getAnswer() {
        return answer;
    }

    // MODIFIES: this
    // EFFECTS: set value into the answer
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    // EFFECTS: get selections
    public ArrayList<String> getSelections() {
        return selections;
    }

    // EFFECTS: make list of index of vocabulary list
    public ArrayList<Integer> makeIndexes() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < vocabularyList.size(); i++) {
            indexes.add(i);
        }
        return indexes;
    }

    // REQUIRES: start >= 0, finish <= NUM_SELECT and start <= finish
    // MODIFIES: this, indexes
    // EFFECTS: make dummy answer and add it to selection
    public void makeDummy(int start, int finish, ArrayList<Integer> indexes) {
        for (int i = start; i < finish; i++) {
            int dummyIndex = (int)(Math.random() * indexes.size());
            int dummyVocabularyNum = indexes.get(dummyIndex);
            indexes.remove(dummyIndex);

            Vocabulary v = vocabularyList.view(dummyVocabularyNum);
            getSelections().add(v.getMeaning());
        }
    }

    // EFFECTS: get vocabulary for a quiz
    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    // EFFECTS: set vocabulary for a quiz
    public void setVocabulary(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }
}
