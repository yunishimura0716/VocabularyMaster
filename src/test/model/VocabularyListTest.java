package model;

import exceptions.VocabularyListBoundsException;
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
        vocabularyList.add(vocabulary1);
        assertEquals(1, vocabularyList.size());
        assertTrue(vocabularyList.contain("vocab1"));

        Vocabulary vocabulary2 = new Vocabulary("vocab2", "test2");
        vocabularyList.add(vocabulary2);
        assertEquals(2, vocabularyList.size());
        assertTrue(vocabularyList.contain("vocab1") && vocabularyList.contain("vocab2"));
    }

    @Test
    public void viewTest() {
        for(int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
            vocabularyList.add(vocabulary);
            try {
                assertEquals("vocab" + (i+1), vocabularyList.view(i).getVocab());
            } catch (VocabularyListBoundsException e) {
                fail("Fail to view vocabulary");
            }
        }
        assertEquals(10, vocabularyList.size());

        try {
            assertEquals("vocab1", vocabularyList.view(0).getVocab());
            assertEquals("vocab7", vocabularyList.view(6).getVocab());
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }

    }

    @Test
    public void failViewTest() {
        for (int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
        }

        Vocabulary vocabulary;
        try {
            vocabulary = vocabularyList.view(10);
            fail("Fail to cast exception");
        } catch (VocabularyListBoundsException e) {
            // do nothing
        }
    }

    @Test
    public void deleteTest() {
        for(int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
            vocabularyList.add(vocabulary);
        }

        Vocabulary vocabulary;
        try {
            vocabulary = vocabularyList.delete(9);
            assertEquals(9, vocabularyList.size());
            assertEquals("vocab9", vocabularyList.view(8).getVocab());
            assertEquals("vocab10", vocabulary.getVocab());
            assertFalse(vocabularyList.contain(vocabulary.getVocab()));
        } catch (VocabularyListBoundsException e) {
            fail("Fail to delete vocabulary");
        }

        try {
            vocabulary = vocabularyList.delete(2);
            assertEquals(8, vocabularyList.size());
            assertEquals("vocab3", vocabulary.getVocab());
            assertEquals("vocab4", vocabularyList.view(2).getVocab());
        } catch (VocabularyListBoundsException e) {
            fail("Fail to delete vocabulary");
        }

        vocabularyList.deleteAll();
        assertEquals(0, vocabularyList.size());
    }

    @Test
    public void failDeleteTest() {
        for(int i = 0; i < 10; i++) {
            Vocabulary vocabulary = new Vocabulary("vocab" + (i+1), "test" + (i+1));
            vocabularyList.add(vocabulary);
        }

        Vocabulary vocabulary;
        try {
            vocabulary = vocabularyList.delete(10);
            fail("Fail to delete vocabulary");
        } catch (VocabularyListBoundsException e) {
            // do nothing
        }
    }
}
