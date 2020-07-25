package tool;

import model.Vocabulary;
import model.VocabularyList;

import java.io.*;

/*
class save Vocabulary List into file and load the file to give Vocabulary List
 */
public class VocabularyFileTool {
    private VocabularyList vocabularyList;
    private String filename;
    private String filepath;
    private File file;


    // EFFECTS: get vocabulary list from calling method
    public VocabularyFileTool(VocabularyList vocabularyList, String filename) throws IOException {
        setFilename(filename);
        this.vocabularyList = vocabularyList;
        makePath();
        file = new File(filepath);
    }

    // EFFECTS: create file path to get in data directory
    public void makePath() throws IOException {
        String dataDir = new File("data").getCanonicalPath();
        filepath = dataDir + File.separator + getFilename();
    }

    // EFFECTS: save the vocabulary list into a file
    public void save() throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < vocabularyList.size(); i++) {
            Vocabulary v = vocabularyList.view(i);
            String fileContent = String.format("%s,%s,%s\n",
                    v.getVocab(), v.getMeaning(), Boolean.toString(v.isRemember()));
            fw.write(fileContent);
        }
        fw.close();
    }

    // MODIFIES: this
    // EFFECTS: load vocabulary data into the list
    public void load() throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String fileContent;
        while ((fileContent = br.readLine()) != null) {
            String[] vocabList = fileContent.split(",");
            Vocabulary v = new Vocabulary(vocabList[0], vocabList[1]);
            v.setRemember(Boolean.parseBoolean(vocabList[2]));
            vocabularyList.add(v);
        }

        fr.close();
        br.close();
    }

    // EFFECTS: return filename
    public String getFilename() {
        return filename;
    }

    // MODIFIES: this
    // EFFECTS: set filename
    public void setFilename(String filename) {
        this.filename = filename;
    }

    // MODIFIES: this
    // EFFECTS: flush data in file
    public void flushFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("");
        bw.flush();

        fw.close();
        bw.close();
    }
}
