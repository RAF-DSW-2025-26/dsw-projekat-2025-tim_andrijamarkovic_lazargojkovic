package raf.graffito.dsw.gui.swing.window;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;

public class WindowSmallMode implements WindowModeStrategy{
    // setovanje na pola od normalne veličine ekrana, odnosno 1/4 ekrana
    @Override
    public void apply() {
        MainFrame.getInstance().setExtendedState(MainFrame.NORMAL);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MainFrame.getInstance().setSize(screenSize.width/4, screenSize.height/4);
        MainFrame.getInstance().setLocationRelativeTo(null);
    }
}
