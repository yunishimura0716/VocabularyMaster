package exceptions;

import model.Vocabulary;

public class VocabularyListBoundsException extends Exception {
    public VocabularyListBoundsException(String msg) {
        super(msg);
    }
}
