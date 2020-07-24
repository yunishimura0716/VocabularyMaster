package model;

/*
Represent a vocabulary (word or idiom) used to make vocabulary book.
 */
public class Vocabulary {
    private String vocab;
    private String meaning;
    private boolean remember = false;

    // EFFECTS: make object with no argument
    public Vocabulary() {
    }

    // EFFECTS: make default value for each fields
    public Vocabulary(String vocab, String meaning) {
        this.vocab = vocab;
        this.meaning = meaning;
    }

    // EFFECTS: return vocabulary
    public String getVocab() {
        return vocab;
    }

    // MODIFIES: this
    // EFFECTS: set value of vocab
    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    // EFFECTS: return vocabulary
    public String getMeaning() {
        return meaning;
    }

    // MODIFIES: this
    // EFFECTS: set value of meaning
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    // EFFECTS: return vocabulary
    public boolean isRemember() {
        return remember;
    }

    // MODIFIES: this
    // EFFECTS: change value of remember
    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
