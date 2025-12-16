package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.commands.concrete.MoveCommand;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MoveState implements State

{
    private Slide slide;
    private SlideView slideView;
    private GraffSlideElement selectedElement = MainFrame.getInstance().getSlideView().getSelectedElement();
    private int offsetX, offsetY;
    private int startX, startY;

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(selectedElement == null) return;
        offsetX = e.getX() - selectedElement.getX();
        offsetY = e.getY() - selectedElement.getY();
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e);
        selectedElement = slideView.getSelectedElement();
        if(selectedElement == null) return;
        //postavljanje nove pozicije elementa na osnovu pomeranja miša
        selectedElement.setX(p.x - offsetX);
        selectedElement.setY(p.y - offsetY);
        slideView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        Command cmd = new MoveCommand(selectedElement,startX,startY,selectedElement.getX(),selectedElement.getY());
        MainFrame.getInstance().getCommandManager().executeCommand(cmd);
        selectedElement = null;
        MainFrame.getInstance().getStateManager().setSelectState();
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
