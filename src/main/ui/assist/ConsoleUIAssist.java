package ui.assist;


import exceptions.VocabularyListBoundsException;
import model.Vocabulary;
import model.VocabularyList;
import ui.ConsoleUI;

/*
Collection of CUI tool to display instructions
 */
public class ConsoleUIAssist {
    private ConsoleUI cui;
    private VocabularyList vocabularyList;

    public ConsoleUIAssist(ConsoleUI cui, VocabularyList vocabularyList) {
        this.cui = cui;
        this.vocabularyList = vocabularyList;
    }

    // EFFECTS: print to remind user of giving invalid input
    public static void invalidInput() {
        System.out.println("    This is invalid input.");
        System.out.println("    Please give valid input\n");
    }

    // MODIFIES: this.cui
    // EFFECTS: set page number based request number
    public void homeRequest(int page) {
        if (page == 0) {
            cui.save();
            cui.setPageNum(5);
        } else if (page == 3) {
            cui.setPageNum(4);
        } else  {
            cui.setPageNum(page);
        }
    }

    // MODIFIES: this
    // EFFECTS: set page number based request number
    public void listRequest(int requestNum) {
        if (requestNum == 0) {
            cui.setPageNum(0);
        } else if (requestNum == -1) {
            cui.save();
        } else {
            cui.setPageNum(3);
            cui.setVocabNum(requestNum);
        }
    }

    // MODIFIES: this
    // EFFECTS: set page number based request num
    public void detailRequest(int requestNum) throws VocabularyListBoundsException {
        if (requestNum == 0) {
            cui.setPageNum(requestNum);
        } else if (requestNum == 1) {
            cui.setPageNum(requestNum);
        } else if (requestNum == 2) {
            vocabularyList.delete(cui.getVocabNum() - 1);
            cui.setPageNum(1);
        } else if (requestNum == 3) {
            vocabularyList.view(cui.getVocabNum() - 1).setRemember(true);
            cui.setPageNum(1);
        } else {
            vocabularyList.view(cui.getVocabNum() - 1).setRemember(false);
            cui.setPageNum(1);
        }
    }

    // EFFECTS: print title for home page
    public static void homeHead() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("|          Vocabulary Master             |");
    }

    // EFFECTS: print command menu for home page
    public static void homeCommand() {
        System.out.println("------------------------------------------\n\n");
        System.out.println("              Command Menu\n");
        System.out.println("         0: Finish Application (save vocabulary list automatically)");
        System.out.println("         1: list of Vocabulary");
        System.out.println("         2: add words and idioms");
        System.out.println("         3: vocabulary quiz\n\n\n");
    }

    // EFFECTS: list the vocabularies
    public void listHead() throws VocabularyListBoundsException {
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

    // EFFECTS: print command menu for list page
    public static void listCommand() {
        System.out.println("------------------------------------------------------");
        System.out.println("              Command Menu\n");
        System.out.println("        -1: save vocabulary list");
        System.out.println("         0: home");
        System.out.println("            OR\n         Select Vocabulary Number to see the detail\n\n");
    }

    // EFFECTS: print command menu for add page
    public static void addCommand() {
        System.out.println("\n------------------------------------------------------");
        System.out.println("|              Add Words and Idioms                  |");
        System.out.println("------------------------------------------------------\n");
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
    public static void addMore() {
        System.out.println("\n\n        Add more??\n");
        System.out.println("        Example　(Add One Vocabulary):");
        System.out.println("            Word or Idiom: word1");
        System.out.println("            Meaning: meaning1");
        System.out.println("        Example (Add Multiple Vocabulary, Please divide by comma):");
        System.out.println("            Word or Idiom: word1, idiom1, word2");
        System.out.println("            Meaning: meaning1, meaning2, meaning3\n");
        System.out.println("    (Enter 0 if you finish)\n\n");
    }

    // REQUIRES: vocabNum >= 1
    // EFFECTS: show detail of vocabulary
    public void detailHead(int vocabNum) throws VocabularyListBoundsException {
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

    // EFFECTS: print command menu for detail page
    public static void detailCommand() {
        System.out.println("--------------------------------------------------------");
        System.out.println("              Command Menu\n");
        System.out.println("         0: home");
        System.out.println("         1: list of vocabulary");
        System.out.println("         2: delete this vocabulary");
        System.out.println("         3: mark as remember");
        System.out.println("         4: unmark as remember\n");
    }
}
