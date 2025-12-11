package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.SlideLogo;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddLogoState implements State
{

    private Slide slide = MainFrame.getInstance().getSlideView().getSlide();
    private SlideView slideView = MainFrame.getInstance().getSlideView();

    @Override
    public void mouseClicked(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        StateManager stateManager = MainFrame.getInstance().getStateManager();
        SlideLogo logo = new SlideLogo("Logo" + slide.getChildren().size(),slide,e.getX(),e.getY(),100,100,0,0);
        slide.getChildren().add(logo);
        slideView.repaint();
        stateManager.setSelectState();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
