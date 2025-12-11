package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MoveState implements State

{
    private Slide slide = MainFrame.getInstance().getSlideView().getSlide();
    private SlideView slideView = MainFrame.getInstance().getSlideView();
    private StateManager stateManager = MainFrame.getInstance().getStateManager();
    private GraffSlideElement selectedElement = slideView.getSelectedElement();
    private int offsetX, offsetY;

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(selectedElement == null) return;
        offsetX = e.getX() - selectedElement.getX();
        offsetY = e.getY() - selectedElement.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        selectedElement = slideView.getSelectedElement();
        if(selectedElement == null) return;
        selectedElement.setX(e.getX() - offsetX);
        selectedElement.setY(e.getY() - offsetY);
        slideView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        stateManager = MainFrame.getInstance().getStateManager();
        selectedElement = null;
        stateManager.setSelectState();
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
