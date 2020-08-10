package ui.image;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    protected Image img;

    public ImagePanel(GridBagLayout gridBagLayout, String filename) {
        super(gridBagLayout);
        img = Toolkit.getDefaultToolkit().createImage(filename);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }
}
