package ui;


/*
Collection of CUI tool to display instructions
 */
public class ConsoleUIAssist {

    public ConsoleUIAssist() {
    }

    // EFFECTS: print to remind user of giving invalid input
    public static void invalidInput() {
        System.out.println("    This is invalid input.");
        System.out.println("    Please give valid input\n");
    }

    // EFFECTS: print title for home page
    public static void homeTitle() {
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