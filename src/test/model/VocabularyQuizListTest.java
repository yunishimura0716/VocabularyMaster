package model;

import exceptions.NotEnoughVocabularyException;
import exceptions.VocabularyListBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class VocabularyQuizListTest {
    VocabularyQuizList quizList;
    VocabularyList vocabularyList;

    @BeforeEach
    public void setUp() {
        vocabularyList = new VocabularyList();
        for (int i = 0; i < 11; i++) {
            Vocabulary v = new Vocabulary("word" + (i+1), "meaning" + (i+1));
            vocabularyList.add(v);
        }
        quizList = new VocabularyQuizList(vocabularyList);
    }

    @Test
    public void failMakeQuizTest() {
        try {
            vocabularyList.view(0).setRemember(true);
            vocabularyList.view(1).setRemember(true);
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }

        try {
            quizList.makeQuizList();
            fail("Fail to throw NotEnoughVocabularyException");
        } catch (NotEnoughVocabularyException e) {
            // do nothing
        } catch (VocabularyListBoundsException e) {
            fail("Fail to provide proper exception");
        }
    }

    @Test
    public void makeQuizListTest() {
        try {
            vocabularyList.view(0).setRemember(true);
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }

        try {
            quizList.makeQuizList();
        } catch (NotEnoughVocabularyException e) {
            fail("Fail to make quiz");
        } catch (VocabularyListBoundsException e) {
            fail("Fail to make quiz");
        }

        ArrayList<String> vocabListForQuiz = new ArrayList<>();

        for (int i = 0; i < quizList.MAX_NUMBER_QUIZ; i++) {
            VocabularyQuiz quiz = quizList.viewQuiz(i);
            assertFalse(quiz.getVocabulary().isRemember());
            assertFalse(vocabListForQuiz.contains(quiz.getVocabulary().getVocab()));
            vocabListForQuiz.add(quiz.getVocabulary().getVocab());
        }

        try {
            assertFalse(vocabListForQuiz.contains(vocabularyList.view(0).getVocab()));
        } catch (VocabularyListBoundsException e) {
            fail("Fail to view vocabulary");
        }
    }
}
