package model;

import exceptions.NotEnoughVocabularyException;
import exceptions.VocabularyListBoundsException;

import java.util.ArrayList;

/*
class provide the list of vocabulary quiz
 */
public class VocabularyQuizList {
    private ArrayList<VocabularyQuiz> vocabularyQuizList;
    private VocabularyList vocabularyList;
    public static final int MAX_NUMBER_QUIZ = 10;

    public VocabularyQuizList(VocabularyList vocabularyList) {
        this.vocabularyList = vocabularyList;
        vocabularyQuizList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: make lis of vocabulary quiz
    public void makeQuizList() throws NotEnoughVocabularyException, VocabularyListBoundsException {
        ArrayList<Integer> indexesNotRemember = makeIndexesNotRemember();
        if (indexesNotRemember.size() < MAX_NUMBER_QUIZ) {
            throw new NotEnoughVocabularyException("Not Enough Vocabulary! (" + MAX_NUMBER_QUIZ + " needed)");
        }

        for (int i = 0; i < MAX_NUMBER_QUIZ; i++) {
            int indexNotRemember = (int)(Math.random() * indexesNotRemember.size());
            int vocabularyNumber = indexesNotRemember.get(indexNotRemember);
            indexesNotRemember.remove(indexNotRemember);

            VocabularyQuiz quiz = new VocabularyQuiz(vocabularyList);
            quiz.makeQuiz(vocabularyNumber);
            vocabularyQuizList.add(quiz);
        }
    }

    // EFFECTS: return a vocabulary quiz object
    public VocabularyQuiz viewQuiz(int index) {
        return vocabularyQuizList.get(index);
    }

    // EFFECTS: make list of index of vocabulary list
    public ArrayList<Integer> makeIndexesNotRemember() throws VocabularyListBoundsException {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < vocabularyList.size(); i++) {
            if (!vocabularyList.view(i).isRemember()) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
