package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class SlideActionView extends JPanel {
    public SlideActionView() {
        setLayout(new GridLayout(6, 2, 5, 5));
        add(new JButton(MainFrame.getInstance().getActionManager().getAddTextAction()));
        add(new JButton(MainFrame.getInstance().getActionManager().getAddLogoAction()));
        add(new JButton(MainFrame.getInstance().getActionManager().getMoveAction()));
        add(new JButton(MainFrame.getInstance().getActionManager().getSelectAction()));
        add(new JButton(MainFrame.getInstance().getActionManager().getAddImageAction()));
    }
}
