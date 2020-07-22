package model;

import model.VocabularyList;
import model.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Unit Test fof VocabularyList class
public class VocabularyListTest {
    VocabularyList vocabularyList;

    @BeforeEach
    public void setUp() {
        vocabularyList = new VocabularyList();
    }

    @Test
    public void addTest() {
        assertEquals(0, vocabularyList.size());
        Vocabulary vocabulary1 = new Vocabulary("vocab1", "test1");
        assertTrue(vocabularyList.add(vocabulary1));
        assertEquals(1, vocabularyList.size());
        assertTrue(vocabularyList.contain("vocab1"));

        Vocabulary vocabulary2 = new Vocabulary("vocab2", "test2");
        assertTrue(vocabularyList.add(vocabulary2));
        assertEquals(2, vocabularyList.size());
        assertTrue(vocabularyList.contain("vocab1") && vocabularyList.contain("vocab2"));
    }

    @Test
    public void viewTest() {
        for(int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
            assertTrue(vocabularyList.add(vocabulary));
            assertEquals("vocab" + (i+1), vocabularyList.view(i).getVocab());
        }
        assertEquals(10, vocabularyList.size());

        assertEquals("vocab1", vocabularyList.view(0).getVocab());
        assertEquals("vocab7", vocabularyList.view(6).getVocab());
    }

    @Test
    public void deleteTest() {
        for(int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
            assertTrue(vocabularyList.add(vocabulary));
        }

        assertTrue(vocabularyList.delete(9));
        assertEquals(9, vocabularyList.size());
        assertEquals("vocab9", vocabularyList.view(8).getVocab());

        assertFalse(vocabularyList.delete(9));

        assertTrue(vocabularyList.delete(2));
        assertEquals(8, vocabularyList.size());
        assertEquals("vocab3", vocabularyList.view(2).getVocab());
    }
}
