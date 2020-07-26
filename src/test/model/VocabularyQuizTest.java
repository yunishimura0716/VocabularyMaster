package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VocabularyQuizTest {
    VocabularyQuiz quiz;
    VocabularyList vocabularyList;

    @BeforeEach
    public void setUp() {
        vocabularyList = new VocabularyList();
        for (int i = 0; i < quiz.NUM_SELECT; i++) {
            Vocabulary v = new Vocabulary("word" + (i+1), "meaning" + (i+1));
            vocabularyList.add(v);
        }
        quiz = new VocabularyQuiz(vocabularyList);
    }

    @Test
    public void makeQuizTest() {
        assertEquals(-1, quiz.getAnswer());

        quiz.makeQuiz(1);

        assertEquals(quiz.NUM_SELECT, quiz.getSelections().size());
        assertEquals("meaning2", quiz.getSelections().get(quiz.getAnswer()));
        assertTrue(quiz.getSelections().contains("meaning1"));
        assertTrue(quiz.getSelections().contains("meaning3"));
        assertTrue(quiz.getSelections().contains("meaning4"));
    }
}
