package model;

import exceptions.VocabularyListBoundsException;

import java.util.ArrayList;

/*
Represent a vocabulary list to collect English words and idioms.
 */
public class VocabularyList extends Vocabulary {
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

    // EFFECTS: return a Vocabulary object refered by index
    public Vocabulary view(int index) throws VocabularyListBoundsException {
        if (!(0 <= index && index < size())) {
            throw new VocabularyListBoundsException("Index is out of bounds");
        }
        return vocabList.get(index);
    }

    // MODIFIES: this
    // EFFECTS: delete a Vocabulary object from list, return true if succeed otherwise false
    public Vocabulary delete(int index) throws VocabularyListBoundsException {
        if (!(0 <= index && index < size())) {
            throw new VocabularyListBoundsException("Index is out of bounds");
        }
        Vocabulary vocabulary = vocabList.get(index);
        vocabList.remove(index);
        return  vocabulary;
    }

    // MODIFIES: this
    // EFFECTS: delete all vocabularies in the list
    public void deleteAll() {
        int length = size();
        for (int i = 0; i < length; i++) {
            try {
                delete(0);
            } catch (VocabularyListBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    // EFFECTS: return size of vocabulary list
    public int size() {
        return vocabList.size();
    }
}
