package ui.assist;

import model.Vocabulary;
import model.VocabularyList;
import ui.GraphicalUI;
import ui.music.ButtonEventMusic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GraphicalUIAssist {
    private GraphicalUI gui;
    private VocabularyList vocabList;
    public ButtonEventMusic buttonEffect;

    public GraphicalUIAssist(GraphicalUI gui, VocabularyList vocabList) {
        this.gui = gui;
        this.vocabList = vocabList;
        try {
            buttonEffect = new ButtonEventMusic("button_event3.wav");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: add action listener to exit
    public void exitListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                int n = JOptionPane.showConfirmDialog(
                        gui.getFrame(),
                        "Would you like to save your vocabulary?",
                        "Confirm Message",
                        JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    gui.save();
                } else {
                    // do nothing
                }
                gui.getBgm().stop();
                System.exit(0);
            }
        });
    }

    // EFFECTS: add action listener to show list page
    public void listListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.list();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: delete action listener to delete a vocabulary
    public void deleteListener(JButton b, int index) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                // yes: 0, no: 1
                int n = JOptionPane.showConfirmDialog(
                        gui.getFrame(),
                        "Would you like to delete this vocabulary?",
                        "Confirm Message",
                        JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    vocabList.delete(index);
                    gui.list();
                } else {
                    // do nothing
                }

            }
        });
    }

    // MODIFIES: this
    // EFFECTS: delete action listener to delete a vocabulary
    public void isRememberListener(JButton b, int index, boolean isRemember) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                vocabList.view(index).setRemember(isRemember);
                gui.detail(index);
            }
        });
    }

    // EFFECTS: add action listener to show add page
    public void addListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.add();
            }
        });
    }

    //EFFECTS: register action listener to add vocabulary to list
    public void registerListener(JButton b, ArrayList<JTextField> textFields) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                String vocabularies = textFields.get(0).getText();
                String meanings = textFields.get(1).getText();
                if (vocabularies.equals("") || meanings.equals("")) {
                    JOptionPane.showMessageDialog(gui.getFrame(),
                            "Please add at least one vocabulary and its meaning",
                            "Add Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (gui.post(vocabularies, meanings)) {
                    JOptionPane.showMessageDialog(gui.getFrame(),
                            "Succeed to add vocabulary");
                    gui.add();
                } else {
                    JOptionPane.showMessageDialog(gui.getFrame(),
                            "The number of vocabularies and meanings is different!",
                            "Add Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //EFFECTS: add action listener to start quiz
    public void quizListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.quiz();
            }
        });
    }

    // EFFECTS: detail action listener to show detail
    public void detailListener(JButton b, JList list) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                int[] indices = list.getSelectedIndices();
                if (indices.length == 0 || indices.length > 1) {
                    JOptionPane.showMessageDialog(gui.getFrame(),
                            "Please select an item from vocabulary list.",
                            "Invalid Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int index = indices[0];
                    gui.detail(index);
                }
            }
        });
    }

    // EFFECTS: save action listener to save vocabulary list to file
    public void saveListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.save();
            }
        });
    }

    // EFFECTS: load action listener to load file data to vocabulary list
    public void loadListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.load();
            }
        });
    }

    // EFFECTS: home action listener to go back to home page
    public void homeListener(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonEffect.play();
                gui.home();
            }
        });
    }

    // EFFECTS: set title on home page
    public void homeTitle() {
        JLabel title = new JLabel("~ Vocabulary Master ~");
        title.setFont(new Font("Title", Font.PLAIN, 30));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridwidth = 3;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(10,100,20,100);
        gui.getPanel().add(title, gui.getConstraints());

        JLabel subtitle = new JLabel();
        subtitle.setText("making your own vocabulary list to boost your English!!");
        subtitle.setFont(new Font("Sub", Font.PLAIN, 20));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridwidth = 1;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 1;
        gui.getConstraints().insets = new Insets(10,0,150,0);

        gui.getPanel().add(subtitle, gui.getConstraints());
    }

    // EFFECTS: set home command components
    public void homeCommand() {
        String[] values = {"exit", "load", "list", "add", "quiz"};
        int size = 5;
        for (int i = 0; i < size; i++) {
            JButton b = new JButton(values[i]);
            gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
            gui.getConstraints().gridwidth = 1;
            gui.getConstraints().gridx = 0;
            gui.getConstraints().gridy = 2 + i;
            gui.getConstraints().insets = new Insets(10,200,10,200);
            switch (i) {
                case 0: exitListener(b);
                break;
                case 1: loadListener(b);
                break;
                case 2: listListener(b);
                break;
                case 3: addListener(b);
                break;
                case 4: quizListener(b);
                break;
            }
            gui.getPanel().add(b, gui.getConstraints());
        }
    }

    // MODIFIES: this
    // EFFECTS: showing header of list page
    public void listHead() {
        JLabel header = new JLabel("Vocabulary List");
        header.setFont(new Font("Header", Font.PLAIN, 25));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(10,10,10,10);
        gui.getPanel().add(header, gui.getConstraints());

        String exampleText = "<HTML><pre>Example: word or idiom  (remember or not remember)<BR>";
        exampleText += "  => meaning</pre><HTML>";
        JLabel example = new JLabel(exampleText);
        example.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 1;
        gui.getPanel().add(example, gui.getConstraints());
    }

    // MODIFIES: this
    // EFFECTS: adding list component on panel
    public JList listBody() {
        ArrayList<String> vocabSerializer = new ArrayList<>();
        for (int i = 0; i < vocabList.size(); i++) {
            Vocabulary v = vocabList.view(i);
            String remember = v.isRemember() ? "remember" : "not remember";
            String vocabFormat = String.format(
                    "<HTML><pre> %s  (%s)<BR>=> %s</pre></HTML>",
                    v.getVocab(), remember, v.getMeaning()
            );
            vocabSerializer.add(vocabFormat);
        }
        String[] vlist = vocabSerializer.toArray(new String[0]);
        JList list = new JList(vlist);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 2;
        gui.getPanel().add(scrollPane, gui.getConstraints());

        return list;
    }

    // MODIFIES: this
    // EFFECTS: adding list command components on panel
    public void listCommand(JList list) {
        String[] values = {"save", "detail", "home"};
        int size = 3;
        for (int i = 0; i < size; i++) {
            JButton b = new JButton(values[i]);
            gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
            gui.getConstraints().gridwidth = 1;
            gui.getConstraints().gridx = 0;
            gui.getConstraints().gridy = 3 + i;
            gui.getConstraints().insets = new Insets(5, 350, 5, 350);
            switch (i) {
                case 0: saveListener(b);
                    break;
                case 1: detailListener(b, list);
                    break;
                case 2: homeListener(b);
                    break;
            }
            gui.getPanel().add(b, gui.getConstraints());
        }
    }

    // MODIFIES: this
    // EFFECTS: showing header on detail page
    public void detailHead() {
        JLabel header = new JLabel("Detail");
        header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(2, 50, 60, 10);
        gui.getPanel().add(header, gui.getConstraints());
    }

    // MODIFIES: this
    // EFFECTS: showing detail of a vocabulary on body of page
    public void detailBody(int index) {
        Vocabulary v = vocabList.view(index);
        String remember = v.isRemember() ? "remember" : "not remember";
        JLabel vlabel = new JLabel(String.format("<html><pre>%s  (%s)</pre></html>", v.getVocab(), remember));
        vlabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        vlabel.setHorizontalAlignment(SwingConstants.LEFT);
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 1;
        gui.getPanel().add(vlabel, gui.getConstraints());

        JTextArea vmeaning = new JTextArea(String.format("%s", v.getMeaning()));
        vmeaning.setEditable(false);
        vmeaning.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        vmeaning.setLineWrap(true);
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 2;
        gui.getConstraints().gridwidth = GridBagConstraints.REMAINDER;
        gui.getConstraints().insets = new Insets(2, 50, 100, 10);
        gui.getPanel().add(vmeaning, gui.getConstraints());
    }

    // MODIFIES: this
    // EFFECTS: showing detail command on detail page
    public void detailCommand(int index) {
        String[] values = {"delete", "list", vocabList.view(index).isRemember() ? "forget" : "remember", "home"};
        int size = 4;
        for (int i = 0; i < size; i++) {
            JButton b = new JButton(values[i]);
            gui.getConstraints().gridx = 0;
            gui.getConstraints().gridy = 3 + i;
            gui.getConstraints().insets = new Insets(2, 350, 2, 350);
            switch (i) {
                case 0: deleteListener(b, index);
                    break;
                case 1: listListener(b);
                    break;
                case 2: isRememberListener(b, index, values[2].equals("remember"));
                    break;
                case 3: homeListener(b);
                    break;
            }
            gui.getPanel().add(b, gui.getConstraints());
        }
    }

    // EFFECTS: showing header of add page
    public void addHead() {
        JLabel header = new JLabel("Add Vocabulary");
        header.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 0;
        gui.getConstraints().insets = new Insets(5, 50, 25, 10);
        gui.getPanel().add(header, gui.getConstraints());

        String expText = "<html><pre>Please add words or idioms with its meaning.<br>";
        expText += "If you want to add multiple words, Please divide each vocabularies by comma.</pre><html>";
        JLabel explanation = new JLabel(expText);
        explanation.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
        gui.getConstraints().gridx = 0;
        gui.getConstraints().gridy = 1;
        gui.getConstraints().gridwidth = 1;
        gui.getConstraints().insets = new Insets(5,50, 40, 10);
        gui.getPanel().add(explanation, gui.getConstraints());
    }

    // EFFECTS: setting input of vocabulary and meaning field
    public ArrayList<JTextField> addBody() {
        String[] labels = {"words or idioms:", "meanings:"};
        ArrayList<JTextField> textFields = new ArrayList<>();
        int size = 2;
        for (int i = 0; i < size; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
            JTextField input = new JTextField(20);
            gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
            gui.getConstraints().gridx = 0;
            gui.getConstraints().gridy = 2 + i;
            gui.getConstraints().insets = new Insets(5, 50, 10, 10);
            gui.getPanel().add(label, gui.getConstraints());
            gui.getConstraints().gridx = 0;
            gui.getConstraints().insets = new Insets(5, 300, 10, 10);
            gui.getPanel().add(input, gui.getConstraints());

            textFields.add(input);
        }

        return textFields;
    }

    // EFFECTS: showing command of add page
    public void addCommand(ArrayList<JTextField> textFields) {
        String[] values = {"register", "home"};
        int size = 2;
        for (int i = 0; i < size; i++) {
            JButton b = new JButton(values[i]);
            gui.getConstraints().fill = GridBagConstraints.HORIZONTAL;
            gui.getConstraints().gridwidth = 1;
            gui.getConstraints().gridx = 0;
            gui.getConstraints().gridy = 4 + i;
            gui.getConstraints().insets = new Insets(15, 350, 5, 350);
            switch (i) {
                case 0: registerListener(b, textFields);
                    break;
                case 1: homeListener(b);
                    break;
            }
            gui.getPanel().add(b, gui.getConstraints());
        }
    }
}
