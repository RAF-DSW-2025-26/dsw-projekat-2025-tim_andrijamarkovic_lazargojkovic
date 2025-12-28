package raf.graffito.dsw.gui.swing.window;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;

public class WindowNormalMode implements WindowModeStrategy {

    // setovanje na normalnu vrednost (pola visine i širine ekrana)
    @Override
    public void apply() {
        MainFrame.getInstance().setExtendedState(MainFrame.NORMAL);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MainFrame.getInstance().setSize(screenSize.width/2, screenSize.height/2);
        MainFrame.getInstance().setLocationRelativeTo(null);
    }
}