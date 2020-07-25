package model;

import java.util.ArrayList;

/*
Represent a vocabulary list to collect English words and idioms.
 */
public class VocabularyList {
    private ArrayList<Vocabulary> vocabList;

    // EFFECTS: make new instance of vocabulary list
    public VocabularyList() {
        vocabList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a Vocabulary object to the list with id, return true if succeed otherwise false
    public void add(Vocabulary vocabulary) {
        vocabList.add(vocabulary);
    }

    // EFFECTS: return true if given word or idiom is contained in the list
    public boolean contain(String word) {
        for (Vocabulary v : vocabList) {
            if (v.getVocab().equals(word)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: 0 <= index < size
    // EFFECTS: return a Vocabulary object refered by index
    public Vocabulary view(int index) {
        return vocabList.get(index);
    }

    // REQUIRES: 0 <= index < size
    // MODIFIES: this
    // EFFECTS: delete a Vocabulary object from list, return true if succeed otherwise false
    public Vocabulary delete(int index) {
        Vocabulary vocabulary = vocabList.get(index);
        vocabList.remove(index);
        return  vocabulary;
    }

    // EFFECTS: return size of vocabulary list
    public int size() {
        return vocabList.size();
    }
}