package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ResizeDirection;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.List;

public class SelectState implements State
{
    private Slide slide = MainFrame.getInstance().getSlideView().getSlide();
    private SlideView slideView = MainFrame.getInstance().getSlideView();
    private StateManager stateManager = MainFrame.getInstance().getStateManager();
    private GraffSlideElement selectedElement = null;

    @Override
    public void mousePressed(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        int x = e.getX();
        int y = e.getY();
        GraffSlideElement prevElement = selectedElement;
        selectedElement = findElementAt(x, y);
        if(selectedElement == prevElement)
        {
            stateManager = MainFrame.getInstance().getStateManager();
            stateManager.setMoveState();
        }
        slideView.setSelectedElement(selectedElement); //čuvanje trenutno selektovanog elementa
        ResizeDirection dir = slideView.findResizeDirection(selectedElement,e.getX(),e.getY());
        if(dir != null)
        {
            stateManager = MainFrame.getInstance().getStateManager();
            stateManager.getResizeState().startResize(selectedElement,dir,e.getX(),e.getY());
            stateManager.setResizeState();
        }
        slideView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    //funkcija koja vraća element na prosledjenim koordinatama
    private GraffSlideElement findElementAt(int x, int y)
    {
        List<GraffNode> elements = slide.getChildren();
        //inverzan prolazak kroz listu (prioritizuje skorije dodate elemente ako ima preklopa)
        for(int i = elements.size() - 1; i >= 0; i--)
        {
            GraffNode element = elements.get(i);
            if(element instanceof GraffSlideElement)
            {
                if(x>=((GraffSlideElement) element).getX() && x<= ((GraffSlideElement) element).getX() + ((GraffSlideElement) element).getWidth()
                        && y>= ((GraffSlideElement) element).getY() && y<= ((GraffSlideElement) element).getY() + ((GraffSlideElement) element).getHeight())
                {
                    return (GraffSlideElement) element;
                }
            }
        }
        return null;
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
