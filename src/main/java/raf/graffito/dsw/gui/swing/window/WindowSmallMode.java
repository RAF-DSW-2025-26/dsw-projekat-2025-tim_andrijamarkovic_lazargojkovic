package raf.graffito.dsw.gui.swing.window;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;

public class WindowSmallMode implements WindowModeStrategy{
    @Override
    public void apply(MainFrame frame) {
        Dimension init = frame.getSize();
        frame.setExtendedState(MainFrame.NORMAL);
        frame.setSize(init.width / 2, init.height / 2);
        frame.setLocationRelativeTo(null);
    }
}
