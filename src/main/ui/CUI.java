package ui;

import model.Vocabulary;
import model.VocabularyList;

import java.util.*;

/*
console user interface application for English Vocabulary Master
 */
public class CUI {
    private VocabularyList vocabularyList;
    private int pageNum = 0;  // 0: home page, 1: listing page // 2: add page // 3: detail page
    private int vocabNum;
    Scanner input;

    // EFFECTS:make user's vocabulary list (next phase: loaded file from storage)
    public CUI() {
        vocabularyList = new VocabularyList();
        input = new Scanner(System.in);
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
        System.out.println("\n----------------------------------------------------");
        System.out.println("No | vocabulary (remember or not remember)");
        System.out.println("   | meaning");
        System.out.println("--------------------------------------------------------");
        for (int i = 0; i < vocabularyList.size(); i++) {
            System.out.println("--------------------------------------------------------");
            int vocabNum = i + 1;
            String isRemember = vocabularyList.view(i).isRemember() ? "(remember)" : "(not remember)";
            System.out.printf("%-3d| %s %s\n", vocabNum, vocabularyList.view(i).getVocab(), isRemember);
            System.out.printf("   | %s\n", vocabularyList.view(i).getMeaning());
        }
    }

    // EFFECTS: show detail of vocabulary
    public void detail(int vocabNum) {
        System.out.println("\n-----------------------------------------------------");
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

    // EFFECTS: print command menu for home page
    private void homeCommand() {
        System.out.println("-------------------------------------------------\n\n");
        System.out.println("              Command Menu\n");
        System.out.println("         0: Finish Application");
        System.out.println("         1: list of Vocabulary");
        System.out.println("         2: add words and idioms\n\n\n");
    }

    // MODIFIES: this
    // EFFECTS: showing home page on console
    public void homePage() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("|          Vocabulary Master             |");
        homeCommand();

        String requestPage = input.nextLine();
        while (!(requestPage.equals("1") || requestPage.equals("2") || requestPage.equals("0"))) {
            System.out.println("    This is invalid input.");
            System.out.println("    Please give valid input");
            homeCommand();

            requestPage = input.nextLine();
        }

        int page = Integer.parseInt(requestPage);
        if (page == 0) {
            setPageNum(4);
        } else {
            setPageNum(page);
        }
    }

    // EFFECTS: print command menu for list page
    private void listCommand() {
        System.out.println("------------------------------------------------------");
        System.out.println("              Command Menu\n");
        System.out.println("         0: home");
        System.out.println("            OR\n         Select Vocabulary Number to see the detail\n\n");
    }

    // MODIFIES: this
    // EFFECTS: set page number based request number
    private void listRequest(int requestNum) {
        if (requestNum == 0) {
            setPageNum(0);
        } else {
            setPageNum(3);
            vocabNum = requestNum;
        }
    }

    // MODIFIES: this
    // EFFECTS: showing list page on console
    public void listPage() {
        list();
        listCommand();
        boolean inputFail = true;
        int requestNum = 0;
        while (inputFail) {
            try {
                requestNum = input.nextInt();
                if (requestNum < 0 || vocabularyList.size() < requestNum) {
                    throw new InputMismatchException();
                } else {
                    inputFail = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("    This is invalid input.\n    Please give valid input");
                input.nextLine();
                listCommand();
            }
        }
        listRequest(requestNum);
        input.nextLine();
    }

    // EFFECTS: print command menu for add page
    private  void addCommand() {
        System.out.println("\n-----------------------------------------------------\n");
        System.out.println("              Add Words and Idioms\n");
        System.out.println("    Please follow below format to add a word or idiom and meaning\n");
        System.out.println("        Example (Add One Vocabulary):");
        System.out.println("            Word or Idiom: example of word");
        System.out.println("            Meaning: example of meaning");
        System.out.println("        Example (Add Multiple Vocabulary, Please divide by comma):");
        System.out.println("            Word or Idiom: word1, idiom1, word2");
        System.out.println("            Meaning: meaning1, meaning2, meaning3\n");
        System.out.println("    (Enter 0 if you finish)\n\n");
    }

    // EFFECTS: print command menu for detail page
    private void addMore() {
        System.out.println("\n\n        Add more??\n");
        System.out.println("        Example　(Add One Vocabulary):");
        System.out.println("            Word or Idiom: word1");
        System.out.println("            Meaning: meaning1");
        System.out.println("        Example (Add Multiple Vocabulary, Please divide by comma):");
        System.out.println("            Word or Idiom: word1, idiom1, word2");
        System.out.println("            Meaning: meaning1, meaning2, meaning3\n");
        System.out.println("    (Enter 0 if you finish)\n\n");
    }

    // MODIFIES: this
    // EFFECTS: showing adding page on console
    public void addPage() {
        addCommand();

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
                addMore();
            }
        } while (true);
    }

    // EFFECTS: print command menu for detail page
    private void detailCommand() {
        System.out.println("------------------------------------------------------");
        System.out.println("              Command Menu\n");
        System.out.println("         0: home");
        System.out.println("         1: list of vocabulary");
        System.out.println("         2: delete this vocabulary");
        System.out.println("         3: mark as remember");
        System.out.println("         4: unmark as remember\n");
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
        detailCommand();
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
                System.out.println("    This is invalid input.");
                System.out.println("    Please give valid input");
                input.nextLine();
                detail(vocabNum);
                detailCommand();
            }
        }
        detailRequest(requestNum);
    }

    // 0: home page, 1: listing page // 2: add page // 3: detail page // 4: end app
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
            } else {
                System.exit(0);
            }
        }
    }
}
