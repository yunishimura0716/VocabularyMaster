package model;

import model.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit Test for Vocabulary class
class VocabularyTest {
    Vocabulary vocabulary;

    @BeforeEach
    public void setUp() {
        vocabulary = new Vocabulary();
    }

    @Test
    public void setVocabTest() {
        assertEquals(null, vocabulary.getVocab());
        vocabulary.setVocab("test1vocab");
        assertEquals("test1vocab", vocabulary.getVocab());
        vocabulary.setVocab("test2vocab");
        assertEquals("test2vocab", vocabulary.getVocab());
    }

    @Test
    public void setMeaningTest() {
        assertEquals(null, vocabulary.getMeaning());
        vocabulary.setMeaning("test1meaning");
        assertEquals("test1meaning", vocabulary.getMeaning());
        vocabulary.setMeaning("test2meaning");
        assertEquals("test2meaning", vocabulary.getMeaning());
    }

    @Test
    public void setRememberTest() {
        assertFalse(vocabulary.isRemember());
        vocabulary.setRemember(true);
        assertTrue(vocabulary.isRemember());
    }
}