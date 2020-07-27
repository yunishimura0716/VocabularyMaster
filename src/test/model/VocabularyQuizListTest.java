package model;

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
    public void makeQuizListTest() {
        vocabularyList.view(0).setRemember(true);

        quizList.makeQuizList();

        ArrayList<String> vocabListForQuiz = new ArrayList<>();

        for (int i = 0; i < quizList.MAX_NUMBER_QUIZ; i++) {
            VocabularyQuiz quiz = quizList.viewQuiz(i);
            assertFalse(quiz.getVocabulary().isRemember());
            assertFalse(vocabListForQuiz.contains(quiz.getVocabulary().getVocab()));
            vocabListForQuiz.add(quiz.getVocabulary().getVocab());
        }

        assertFalse(vocabListForQuiz.contains(vocabularyList.view(0).getVocab()));
    }
}
