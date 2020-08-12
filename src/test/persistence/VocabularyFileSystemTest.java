package persistence;

import exceptions.VocabularyListBoundsException;
import model.Vocabulary;
import model.VocabularyList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class VocabularyFileSystemTest {
    private VocabularyFileSystem fileTool;
    private VocabularyList vocabularyList;
    private String filename = "testVocabularyData.txt";

    @BeforeEach
    public void setUp() {
        vocabularyList = new VocabularyList();
        try {
            fileTool = new VocabularyFileSystem(vocabularyList, filename);
            fileTool.flushFile();
        } catch (IOException e) {
            fail("Fail to make file object");
        }
    }

    @Test
    public void LoadNoDataTest() {
        try {
            fileTool.load();
        } catch (IOException e) {
            fail("Fail to load no data");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to load data");
        }

        assertEquals(0, vocabularyList.size());
    }

    @Test
    public void saveNoDataTest() {
        VocabularyList newList = new VocabularyList();
        try {
            fileTool.save();
            fileTool = new VocabularyFileSystem(newList, filename);
            fileTool.load();
        } catch (IOException e) {
            fail("Fail to save No data");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to save No data");
        }

        assertEquals(0, newList.size());
    }

    @Test
    public void saveAndLoadTest() {
        for (int i = 0; i < 5; i++) {
            Vocabulary vocabulary = new Vocabulary("vocabulary" + (i+1), "meaning" + (i+1));
            vocabularyList.add(vocabulary);
        }
        try {
            fileTool.save();
        } catch (IOException e) {
            fail("Fail to save data");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to save data");
        }
        VocabularyList newList = new VocabularyList();
        try {
            fileTool = new VocabularyFileSystem(newList, filename);
            fileTool.load();
        } catch (IOException e) {
            fail("Fail to load data");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to load data");
        }

        assertEquals(5, newList.size());
        try {
            for(int i = 0; i < 5; i++) {
                assertEquals(vocabularyList.view(i).getVocab(), newList.view(i).getVocab());
                assertEquals(vocabularyList.view(i).getMeaning(), newList.view(i).getMeaning());
                assertEquals(vocabularyList.view(i).isRemember(), newList.view(i).isRemember());
            }
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }

        try {
            fileTool.load();
        } catch (IOException e) {
            fail("Fail to load data");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to load data");
        }
        assertEquals(vocabularyList.size(), newList.size());
        try {
            for (int i = 0; i < 5; i++) {
                assertEquals(vocabularyList.view(i).getVocab(), newList.view(i).getVocab());
                assertEquals(vocabularyList.view(i).getMeaning(), newList.view(i).getMeaning());
                assertEquals(vocabularyList.view(i).isRemember(), newList.view(i).isRemember());
            }
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }
    }
}
