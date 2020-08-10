package ui.assist;

import model.VocabularyQuizList;
import model.VocabularyQuiz;
import tool.QuizCheckerTool;
import ui.GraphicalUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
Class provides gui vocabulary quiz
 */
public class QuizRunnerGraphicalUI {
    private GraphicalUI gui;
    private int totalCorrect;
    private int currentQuiz;

    // EFFECTS: construct quiz list and total correct
    public QuizRunnerGraphicalUI(GraphicalUI gui) {
        this.gui = gui;
        totalCorrect = 0;
        currentQuiz = 0;
    }

    // MODIFIES: this
    // EFFECT: showing next question
    public void showNextQuestion() {
        currentQuiz++;
        gui.setUp();
        if (currentQuiz <= VocabularyQuizList.MAX_NUMBER_QUIZ) {
            showQuestion(currentQuiz);
        } else {
            result();
        }
        gui.getFrame().setVisible(true);
    }

    // EFFECTS: action listener to show question
    public void startQuestionListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getGuiAssist().buttonEffect.play();
                currentQuiz = 0;
                totalCorrect = 0;
                showNextQuestion();
            }
        });
    }

    // EFFECTS: action listener to show feed back of answer
    public void feedback(boolean isCorrect, VocabularyQuiz question) {
        String correctMeaning = question.getSelections().get(question.getAnswer());
        String message;
        if (isCorrect) {
            message = "Correct!";
            totalCorrect++;
        } else {
            message = "Incorrect! The answer is " + correctMeaning;
        }
        JOptionPane.showMessageDialog(gui.getFrame(), message);
    }

    // EFFECTS: showing choices of a question
    public void showChoices(VocabularyQuiz q) {
        ButtonGroup bg =  new ButtonGroup();
        HashMap<String, Integer> choiceTable = new HashMap<String, Integer>();
        JPanel buttonArea = new JPanel(new GridBagLayout());
        gui.getConstraints().insets = new Insets(10,10,10,10);
        for (int i = 0; i < VocabularyQuiz.NUM_SELECT; i++) {
            String choice = q.getSelections().get(i);
            JRadioButton rb = new JRadioButton(choice);
            rb.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
            rb.setBackground(Color.white);
            gui.getConstraints().gridy = i;
            buttonArea.add(rb, gui.getConstraints());
            rb.setActionCommand(choice);
            bg.add(rb);
            choiceTable.put(choice, i);
        }
        gui.getConstraints().gridy = 2;
        gui.getPanel().add(buttonArea, gui.getConstraints());
        JButton b = new JButton("submit");
        gui.getConstraints().gridy = 3;
        gui.getConstraints().insets = new Insets(10,200,10,200);
        gui.getPanel().add(b, gui.getConstraints());
        QuizCheckerTool checker = new QuizCheckerTool(q.getAnswer());
        submitAnswerListener(b, choiceTable, bg, checker, q);
    }

    // EFFECTS: showing submit button and check answer
    public void submitAnswerListener(JButton b, HashMap<String, Integer> choiceTable,
                                     ButtonGroup bg, QuizCheckerTool checker, VocabularyQuiz q) {
        gui.getGuiAssist().buttonEffect.play();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.getGuiAssist().buttonEffect.play();
                try {
                    String answerText = bg.getSelection().getActionCommand();
                    int answer = choiceTable.get(answerText);
                    feedback(checker.isCorrect(answer), q);
                    showNextQuestion();
                } catch (NullPointerException err) {
                    alertSelection();
                }
            }
        });
    }

    // EFFECTS: show message confirmation to alert invalid selection
    public void alertSelection() {
        JOptionPane.showMessageDialog(gui.getFrame(),
                "`Please select a choice.",
                "Invalid Selection Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: showing question of quiz
    public void showQuestion(int i) {
        i--;
        VocabularyQuiz question = gui.getQuiz().viewQuiz(i);
        JLabel header = new JLabel("Question" + (i + 1));
        gui.getConstraints().insets = new Insets(10, 10,40,10);
        header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getPanel().add(header, gui.getConstraints());
        JTextArea description = new JTextArea("What is meaning of \"" + question.getVocabulary().getVocab() + "\"?");
        description.setEditable(false);
        description.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        gui.getConstraints().gridy = 1;
        gui.getPanel().add(description, gui.getConstraints());
        showChoices(question);
    }

    // EFFECTS: showing instruction of quiz
    public void instruction() {
        gui.setUp();
        JLabel header = new JLabel("Vocabulary Quiz");
        header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(10,0,100,0);
        gui.getPanel().add(header, gui.getConstraints());
        String instructionText = "You can make sure how many vocabularies you remember ";
        instructionText += "in your list.\nThere are \"" + VocabularyQuizList.MAX_NUMBER_QUIZ + "\" questions. ";
        instructionText += "Each question has \"" + VocabularyQuiz.NUM_SELECT + "\" choices.\n\n";
        instructionText += "LET'S START!!";
        JTextArea instruction = new JTextArea(instructionText);
        instruction.setEditable(false);
        instruction.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        gui.getConstraints().gridy = 1;
        gui.getPanel().add(instruction, gui.getConstraints());
        JButton b = new JButton("start");
        gui.getConstraints().gridy = 2;
        startQuestionListener(b);
        gui.getConstraints().insets = new Insets(10,300,10,300);
        gui.getPanel().add(b, gui.getConstraints());
    }

    // EFFECTS: showing result of quiz
    public void result() {
        JLabel header = new JLabel("Finish!!");
        header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(10,10,50,10);
        gui.getPanel().add(header, gui.getConstraints());

        String outcomeText = String.format("You answer correctly %d / %d.",
                totalCorrect, VocabularyQuizList.MAX_NUMBER_QUIZ);
        JTextArea outcome = new JTextArea(outcomeText);
        outcome.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        outcome.setEditable(false);
        gui.getConstraints().gridy = 1;
        gui.getConstraints().insets = new Insets(10,10,30,10);
        gui.getPanel().add(outcome, gui.getConstraints());

        JButton b = new JButton("home");
        gui.getGuiAssist().homeListener(b);
        gui.getConstraints().gridy = 2;
        gui.getConstraints().insets = new Insets(10,200,10,200);
        gui.getPanel().add(b, gui.getConstraints());

        b = new JButton("start again");
        startQuestionListener(b);
        gui.getConstraints().gridy = 3;
        gui.getPanel().add(b, gui.getConstraints());
    }
}
