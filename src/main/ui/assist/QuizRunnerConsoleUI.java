package ui.assist;

import model.Vocabulary;
import model.VocabularyQuizList;
import model.VocabularyQuiz;
import tool.QuizCheckerTool;
import ui.assist.ConsoleUIAssist;

import java.util.Scanner;

/*
Class provides the cui for vocabulary quiz
 */
public class QuizRunnerConsoleUI {
    private VocabularyQuizList quiz;
    private Scanner input;
    private int totalCorrect;

    // REQUIRES: quiz is made
    public QuizRunnerConsoleUI(VocabularyQuizList quiz) {
        this.quiz = quiz;
        input = new Scanner(System.in);
        totalCorrect = 0;
    }

    // EFFECTS: run vocabulary quiz
    public void runQuiz() {
        instruction();

        for (int i = 0; i < VocabularyQuizList.MAX_NUMBER_QUIZ; i++) {
            showQuestion(i);
            boolean isCorrect = submitAnswer(i);
            feedback(isCorrect);
        }

        result();
    }

    // EFFECTS: showing a question including a vocabulary and some selections
    public void showQuestion(int i) {
        Vocabulary questionVocabulary = quiz.viewQuiz(i).getVocabulary();
        System.out.println("------------------------------------------------------");
        System.out.println("|                 Question" + (i + 1) + "                          |");
        System.out.println("------------------------------------------------------\n");
        System.out.println("What is meaning of \"" + questionVocabulary.getVocab() + "\" ?");

        for (int j = 0; j < VocabularyQuiz.NUM_SELECT; j++) {
            String meaning = quiz.viewQuiz(i).getSelections().get(j);
            System.out.println((j + 1) + ": " + meaning);
        }
    }

    // EFFECTS: having user enter his or her answer and check
    public boolean submitAnswer(int i) {
        int answer = quiz.viewQuiz(i).getAnswer();

        QuizCheckerTool checker = new QuizCheckerTool(answer);
        System.out.print(">> ");
        int userAnswer = checker.serializeAnswer(input.nextLine());
        while (!(1 <= userAnswer && userAnswer <= 4)) {
            ConsoleUIAssist.invalidInput();
            System.out.print(">> ");
            userAnswer = checker.serializeAnswer(input.nextLine());
        }
        userAnswer--;

        return checker.isCorrect(userAnswer);
    }

    // MODIFIES: this
    // EFFECTS: giving feed back based on correctness
    public void feedback(boolean isCorrect) {
        if (isCorrect) {
            System.out.println("Correct!");
            totalCorrect++;
        } else {
            System.out.println("Incorrect!");
        }
    }

    // EFFECTS: show the instruction of quiz
    public void instruction() {
        System.out.println("------------------------------------------------------");
        System.out.println("|                 Vocabulary Quiz                    |");
        System.out.println("------------------------------------------------------\n");
        System.out.println("         You can make sure how many vocabularies you remember      ");
        System.out.println("         in your list.");
        System.out.println("         There are " + VocabularyQuizList.MAX_NUMBER_QUIZ + " questions.");
        System.out.println("         Each question has " + VocabularyQuiz.NUM_SELECT + " choices.");
        System.out.println("                   LET'S START!!                      \n");
    }

    // EFFECTS: show the result of quiz
    public void result() {
        System.out.println("\n\nYou answer "
                + totalCorrect + " correctly in " + VocabularyQuizList.MAX_NUMBER_QUIZ + " questions");
        System.out.println();
    }

}
