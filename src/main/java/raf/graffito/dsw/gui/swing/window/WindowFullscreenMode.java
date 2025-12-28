package raf.graffito.dsw.gui.swing.window;

import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;

public class WindowFullscreenMode implements WindowModeStrategy{

    // setovanje na maksimalnu veličinu ekrana
    @Override
    public void apply() {
        MainFrame.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}
