package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.state.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ZoomState implements State
{
    private Slide slide;
    private SlideView slideView;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);

        double newZoom = slideView.getZoom() + (-e.getWheelRotation() * 0.1);
        newZoom = Math.max(slideView.getMIN_ZOOM(), Math.min(slideView.getMAX_ZOOM(), newZoom));
        slideView.setZoom(newZoom);
        slideView.revalidate();
        slideView.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
