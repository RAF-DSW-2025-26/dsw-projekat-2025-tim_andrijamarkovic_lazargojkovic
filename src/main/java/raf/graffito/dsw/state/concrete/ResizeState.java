package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ResizeDirection;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ResizeState implements State
{
    private Slide slide;
    private SlideView slideView;
    private GraffSlideElement selectedElement;
    private ResizeDirection selectedDirection;

    private int startX, startY;
    private int startW, startH;
    private int startElemX, startElemY;

    public void startResize(GraffSlideElement slideElement, ResizeDirection resizeDirection, int mx, int my)
    {
        this.selectedElement = slideElement;
        this.selectedDirection = resizeDirection;
        startX = mx;
        startY = my;
        startW = slideElement.getWidth();
        startH = slideElement.getHeight();
        startElemX = slideElement.getX();
        startElemY = slideElement.getY();
    }
    @Override
    public void mouseDragged(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e);
        if(selectedElement == null) return;
        int dx = p.x - startX;
        int dy = p.y - startY;
        int newX = startElemX;
        int newY = startElemY;
        int newW = startW;
        int newH = startH;
        switch(selectedDirection)
        {
            case SOUTH:
                newH = Math.max(10, startH+dy);
            case NORTH:
                newY = startElemY + dy;
                newH = Math.max(10, startH-dy);
            case WEST:
                newX = startElemX + dx;
                newW = Math.max(10, startW-dx);
            case EAST:
                newW = Math.max(10, startW+dx);
            case NORTH_EAST:
                newY = startElemY + dy;
                newH = Math.max(10, startH-dy);
                newW = Math.max(10, startW+dx);
            case SOUTH_EAST:
                newH = Math.max(10, startH+dy);
                newW = Math.max(10, startW+dx);
            case NORTH_WEST:
                newY = startElemY + dy;
                newH = Math.max(10, startH-dy);
                newX = startElemX + dx;
                newW = Math.max(10, startW-dx);
            case SOUTH_WEST:
                newX = startElemX + dx;
                newW = Math.max(10, startW-dx);
                newH = Math.max(10, startH+dy);
        }

        selectedElement.setX(newX);
        selectedElement.setY(newY);
        selectedElement.setWidth(newW);
        selectedElement.setHeight(newH);
        slideView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        selectedElement = null;
        selectedDirection = ResizeDirection.NONE;
        MainFrame.getInstance().getStateManager().setSelectState();
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
