package model;

import model.Vocabulary;

import java.util.ArrayList;

/*
Represent a vocabulary list to collect English words and idioms.
 */
public class VocabularyList {
    private ArrayList<Vocabulary> vocabList;

    // make new instance of vocabulary list
    VocabularyList() {
        vocabList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a Vocabulary object to the list with id, return true if succeed otherwise false
    public boolean add(Vocabulary vocabulary) {
        return false;
    }

    // EFFECTS: return true if given word or idiom is contained in the list
    public boolean contain(String word) {
        return false;
    }

    // REQUIRES: 0 <= index < size
    // EFFECTS: return a Vocabulary object refered by index
    public Vocabulary view(int index) {
        return null;
    }

    // REQUIRES: 0 <= index < size
    // MODIFIES: this
    // EFFECTS: delete a Vocabulary object from list, return true if succeed otherwise false
    public boolean delete(int index) {
        return false;
    }

    // EFFECTS: return size of vocabulary list
    public int size() {
        return -1;
    }
}
