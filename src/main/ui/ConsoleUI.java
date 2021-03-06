package ui;

import exceptions.NotEnoughVocabularyException;
import exceptions.VocabularyListBoundsException;
import model.VocabularyList;
import ui.assist.ConsoleUIAssist;
import ui.assist.QuizRunnerConsoleUI;

import java.io.IOException;
import java.util.*;

/*
console user interface application for English Vocabulary Master
 */
public class ConsoleUI extends UserInterface {
    private ConsoleUIAssist cuiAssist;
    private int pageNum = 0;  // 0: home page, 1: listing page // 2: add page // 3: detail page
    private int vocabNum;
    Scanner input;
    QuizRunnerConsoleUI quizRunner;

    // EFFECTS: make user's vocabulary list (next phase: loaded file from storage)
    public ConsoleUI() throws IOException {
        super();

        input = new Scanner(System.in);
        cuiAssist = new ConsoleUIAssist(this);

        try {
            fileTool.load();
        } catch (IOException e) {
            System.out.println("Fail to load data");
        } catch (VocabularyListBoundsException e) {
            e.printStackTrace();
        }
    }

    public VocabularyList getVocabularyList() {
        return vocabularyList;
    }

    // EFFECTS: get vocabulary number
    public int getVocabNum() {
        return vocabNum;
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

    public void setVocabNum(int n) {
        vocabNum = n;
    }

    // MODiFIES: this
    // EFFECTS: refresh the file and save vocabulary list into file
    @Override
    public void save() {
        try {
            fileTool.flushFile();
            fileTool.save();
            System.out.println("\nSucceed to save vocabulary list!\n");
        } catch (IOException e) {
            System.out.println("Fail to update your data");
        } catch (VocabularyListBoundsException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: showing home page on console
    @Override
    public void home() {
        cuiAssist.homeHead();
        int page = 0;
        boolean inputFail = true;
        while (inputFail) {
            try {
                cuiAssist.homeCommand();
                page = Integer.parseInt(input.nextLine()); // 0: exit, 1: list, 2: add, 3: quiz
                if (page < 0 || 3 < page) {
                    throw new NumberFormatException();
                } else {
                    inputFail = false;
                }
            } catch (NumberFormatException e) {
                cuiAssist.invalidInput();
            }
        }
        cuiAssist.homeRequest(page);
    }

    // EFFECTS: check the input to be valid on list page
    public int checkInputList() {
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
                cuiAssist.invalidInput();
                input.nextLine();
                cuiAssist.listCommand();
            }
        }
        return requestNum;
    }

    // MODIFIES: this
    // EFFECTS: showing list page on console
    @Override
    public void list() {
        try {
            cuiAssist.listHead();
        } catch (VocabularyListBoundsException e) {
            System.out.println(e.getMessage());
        }
        cuiAssist.listCommand();
        int requestNum = checkInputList();
        cuiAssist.listRequest(requestNum);
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: showing adding page on console
    @Override
    public void add() {
        cuiAssist.addCommand();

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
                cuiAssist.addMore();
            }
        } while (true);
    }

    // EFFECTS: check input to be valid on dateil page
    public int checkInputDetail() {
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
                cuiAssist.invalidInput();
                input.nextLine();
                try {
                    cuiAssist.detailHead(vocabNum);
                } catch (VocabularyListBoundsException err) {
                    System.out.println(err.getMessage());
                }
                cuiAssist.detailCommand();
            }
        }
        return requestNum;
    }

    // MODIFIES: this
    // EFFECTS: showing detail page, user can delete or mark as remember
    @Override
    public void detail(int vocabNum) {
        try {
            cuiAssist.detailHead(vocabNum);
        } catch (VocabularyListBoundsException e) {
            System.out.println(e.getMessage());
        }
        cuiAssist.detailCommand();
        int requestNum = checkInputDetail();
        try {
            cuiAssist.detailRequest(requestNum);
        } catch (VocabularyListBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    // EFFECTS: showing vocabulary quiz page
    @Override
    public void quiz() {
        try {
            quiz.makeQuizList();

            quizRunner = new QuizRunnerConsoleUI(quiz);
            quizRunner.runQuiz();
        } catch (NotEnoughVocabularyException e) {
            System.out.println(e.getMessage());
        } catch (VocabularyListBoundsException e) {
            System.out.println(e.getMessage());
        }
        setPageNum(0);
    }

    // 0: home page, 1: listing page // 2: add page // 3: detail page // 4: vocabulary quiz // otherwise, end app
    // EFFECTS: complete CUI application using each page method
    public void main() {
        while (true) {
            if (this.getPageNum() == 0) {
                this.home();
            } else if (this.getPageNum() == 1) {
                this.list();
            } else if (this.getPageNum() == 2) {
                this.add();
            } else if (this.getPageNum() == 3) {
                this.detail(vocabNum);
            } else if (this.getPageNum() == 4) {
                this.quiz();
            } else {
                System.exit(0);
            }
        }
    }
}
