package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.gui.swing.window.WindowModeStrategy;

public class WindowModeController {

    private final MainFrame mainFrame;
    private final SlideView slideView;

    public WindowModeController(MainFrame mainFrame, SlideView slideView) {
        this.mainFrame = mainFrame;
        this.slideView = slideView;
    }


    public void setMode(WindowModeStrategy strategy) {
        strategy.apply(MainFrame.getInstance());
        SlideView slideView = MainFrame.getInstance().getTabbedPane().getActiveSlideView();
        double scale = MainFrame.getInstance().getTabbedPane().getActiveSlideView().calculateScaleToFit(MainFrame.getInstance());
        slideView.setScaleFactor(scale);
    }

}
