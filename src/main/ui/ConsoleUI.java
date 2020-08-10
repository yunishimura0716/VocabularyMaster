package ui;

import exceptions.NotEnoughVocabularyException;
import model.Vocabulary;
import model.VocabularyList;
import persistence.VocabularyFileSystem;
import model.VocabularyQuizList;
import ui.assist.ConsoleUIAssist;
import ui.assist.QuizRunnerConsoleUI;

import java.io.IOException;
import java.util.*;

/*
console user interface application for English Vocabulary Master
 */
public class ConsoleUI {
    private VocabularyList vocabularyList;
    private ConsoleUIAssist cuiTool;
    private int pageNum = 0;  // 0: home page, 1: listing page // 2: add page // 3: detail page
    private int vocabNum;
    Scanner input;
    private String filename = "vocabularyData.txt";
    private VocabularyFileSystem fileTool;
    private VocabularyQuizList quiz;

    // EFFECTS: make user's vocabulary list (next phase: loaded file from storage)
    public ConsoleUI() {
        vocabularyList = new VocabularyList();
        input = new Scanner(System.in);
        cuiTool = new ConsoleUIAssist();
        try {
            fileTool = new VocabularyFileSystem(vocabularyList, filename);
        } catch (IOException e) {
            System.out.println("Fail to connect with your file system");
        }
        try {
            fileTool.load();
        } catch (IOException e) {
            System.out.println("Fail to load data");
        }
        quiz = new VocabularyQuizList(vocabularyList);
    }

    // MODIFIES: this
    // EFFECTS: add vocabularies into list
    public boolean post(String vocabs, String meanings) {
        String[] vocablist = {vocabs};
        String[] meaninglist = {meanings};
        if (vocabs.contains(",")) {
            vocablist = vocabs.split("\\s*,\\s*");
        }
        if (meanings.contains(",")) {
            meaninglist = meanings.split("\\s*,\\s*");
        }
        if (vocablist.length == meaninglist.length) {
            for (int i = 0; i < vocablist.length; i++) {
                Vocabulary vocabulary = new Vocabulary(vocablist[i], meaninglist[i]);
                vocabularyList.add(vocabulary);
            }
            return true;
        } else {
            return false;
        }

    }

    // EFFECTS: list the vocabularies
    public void list() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("No | vocabulary (remember or not remember)");
        System.out.println("   | meaning");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < vocabularyList.size(); i++) {
            System.out.println("------------------------------------------------------");
            int vocabNum = i + 1;
            String isRemember = vocabularyList.view(i).isRemember() ? "(remember)" : "(not remember)";
            System.out.printf("%-3d| %s %s\n", vocabNum, vocabularyList.view(i).getVocab(), isRemember);
            System.out.printf("   | %s\n", vocabularyList.view(i).getMeaning());
        }
    }

    // REQUIRES: vocabNum >= 1
    // EFFECTS: show detail of vocabulary
    public void detail(int vocabNum) {
        System.out.println("\n--------------------------------------------------------");
        int i = vocabNum - 1;
        String isRemember = vocabularyList.view(i).isRemember() ? "(remember)" : "(not remember)";
        System.out.println("No | vocabulary (remember or not remember)");
        System.out.println("   | meaning");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-3d| %s %s\n", vocabNum, vocabularyList.view(i).getVocab(), isRemember);
        System.out.printf("   | %s\n", vocabularyList.view(i).getMeaning());
        System.out.println("--------------------------------------------------------");
    }

    // EFFECTS: get the value of page number
    public int getPageNum() {
        return pageNum;
    }

    // MODIFIES: this
    // EFFECTS: set value into pageNum
    public void setPageNum(int p) {
        pageNum = p;
    }

    // MODiFIES: this
    // EFFECTS: refresh the file and save vocabulary list into file
    public void save() {
        try {
            fileTool.flushFile();
            fileTool.save();
            System.out.println("\nSucceed to save vocabulary list!\n");
        } catch (IOException e) {
            System.out.println("Fail to update your data");
        }
    }

    // EFFECTS: set page number based request number
    private void homeRequest(int page) {
        if (page == 0) {
            save();
            setPageNum(5);
        } else if (page == 3) {
            setPageNum(4);
        } else  {
            setPageNum(page);
        }
    }

    // MODIFIES: this
    // EFFECTS: showing home page on console
    public void homePage() {
        cuiTool.homeTitle();
        int page = 0;
        boolean inputFail = true;
        while (inputFail) {
            try {
                cuiTool.homeCommand();
                page = Integer.parseInt(input.nextLine()); // 0: exit, 1: list, 2: add, 3: quiz
                if (page < 0 || 3 < page) {
                    throw new NumberFormatException();
                } else {
                    inputFail = false;
                }
            } catch (NumberFormatException e) {
                cuiTool.invalidInput();
            }
        }
        homeRequest(page);
    }

    // MODIFIES: this
    // EFFECTS: set page number based request number
    private void listRequest(int requestNum) {
        if (requestNum == 0) {
            setPageNum(0);
        } else if (requestNum == -1) {
            save();
        } else {
            setPageNum(3);
            vocabNum = requestNum;
        }
    }

    // MODIFIES: this
    // EFFECTS: showing list page on console
    public void listPage() {
        list();
        cuiTool.listCommand();
        boolean inputFail = true;
        int requestNum = 0;
        while (inputFail) {
            try {
                requestNum = input.nextInt();
                if (requestNum < -1 || vocabularyList.size() < requestNum) {
                    throw new InputMismatchException();
                } else {
                    inputFail = false;
                }
            } catch (InputMismatchException e) {
                cuiTool.invalidInput();
                input.nextLine();
                cuiTool.listCommand();
            }
        }
        listRequest(requestNum);
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: showing adding page on console
    public void addPage() {
        cuiTool.addCommand();

        do {
            System.out.print("Word or Idiom: ");
            String vocab = input.nextLine();
            if (vocab.equals("0")) {
                int requestPage = Integer.parseInt(vocab);
                setPageNum(requestPage);
                break;
            } else {
                System.out.print("Meaning: ");
                String meaning = input.nextLine();
                boolean success = post(vocab, meaning);
                if (!success) {
                    System.out.println("Error: The number of vocabularies and meanings is different!");
                }
                cuiTool.addMore();
            }
        } while (true);
    }

    // MODIFIES: this
    // EFFECTS: set page number based request num
    private void detailRequest(int requestNum) {
        if (requestNum == 0) {
            setPageNum(requestNum);
        } else if (requestNum == 1) {
            setPageNum(requestNum);
        } else if (requestNum == 2) {
            vocabularyList.delete(vocabNum - 1);
            setPageNum(1);
        } else if (requestNum == 3) {
            vocabularyList.view(vocabNum - 1).setRemember(true);
            setPageNum(1);
        } else {
            vocabularyList.view(vocabNum - 1).setRemember(false);
            setPageNum(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: showing detail page, user can delete or mark as remember
    public void detailPage(int vocabNum) {
        detail(vocabNum);
        cuiTool.detailCommand();
        int requestNum = 0;
        boolean inputFail = true;
        while (inputFail) {
            try {
                requestNum = input.nextInt();
                if (requestNum < 0 || 4 < requestNum) {
                    throw new InputMismatchException();
                } else {
                    inputFail = false;
                }
            } catch (InputMismatchException e) {
                cuiTool.invalidInput();
                input.nextLine();
                detail(vocabNum);
                cuiTool.detailCommand();
            }
        }
        detailRequest(requestNum);
    }

    // EFFECTS: showing vocabulary quiz page
    public void vocabularyQuizPage() {
        try {
            quiz.makeQuizList();

            QuizRunnerConsoleUI quizRunner = new QuizRunnerConsoleUI(quiz);
            quizRunner.runQuiz();
        } catch (NotEnoughVocabularyException e) {
            System.out.println(e.getMessage());
        }
        setPageNum(0);
    }

    // 0: home page, 1: listing page // 2: add page // 3: detail page // 4: vocabulary quiz // otherwise, end app
    // EFFECTS: complete CUI application using each page method
    public void main() {
        while (true) {
            if (this.getPageNum() == 0) {
                this.homePage();
            } else if (this.getPageNum() == 1) {
                this.listPage();
            } else if (this.getPageNum() == 2) {
                this.addPage();
            } else if (this.getPageNum() == 3) {
                this.detailPage(vocabNum);
            } else if (this.getPageNum() == 4) {
                this.vocabularyQuizPage();
            } else {
                System.exit(0);
            }
        }
    }
}
