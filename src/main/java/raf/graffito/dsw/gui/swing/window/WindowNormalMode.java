package raf.graffito.dsw.gui.swing.window;

import raf.graffito.dsw.gui.swing.MainFrame;

public class WindowNormalMode implements WindowModeStrategy {

    @Override
    public void apply(MainFrame frame) {
        frame.setExtendedState(MainFrame.NORMAL);
        frame.setSize(frame.getSize());
        frame.setLocationRelativeTo(null);
    }
}