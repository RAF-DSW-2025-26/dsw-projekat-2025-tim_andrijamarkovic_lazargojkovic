package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class SlideActionView extends JPanel {

    public SlideActionView() {
        setLayout(new FlowLayout());
        setSize(new Dimension(100,100));
        setBackground(Color.BLUE);
        add(new Button("jaja"));
        add(new Button("moja"));
        add(new Button("kilava"));
        add(new Button("jaja"));
    }
}
