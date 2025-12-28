package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;

public class WindowModeController {


    public WindowModeController() {

    }


    public void setMode(WindowModeStrategy strategy) {
        strategy.apply();
        SlideView slideView = MainFrame.getInstance().getTabbedPane().getActiveSlideView();
        double scale = MainFrame.getInstance().getTabbedPane().getActiveSlideView().calculateScaleToFit(MainFrame.getInstance());
        slideView.setScaleFactor(scale);
    }

}
