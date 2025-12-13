package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.core.GraffNode;
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
import java.util.List;

public class SelectState implements State
{
    private Slide slide;
    private SlideView slideView;
    private GraffSlideElement selectedElement = null;
    private Point startPoint;
    private boolean dragging = false;

    //na klik miša na element(e) na slideview, taj element(i) se selektuje/u
    @Override
    public void mousePressed(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e);
        int x = p.x;
        int y = p.y;
        GraffSlideElement prevElement = selectedElement; //pamćenje prethodno selektovanog elementa
        GraffSlideElement clickedElement = findElementAt(x, y);
        if(clickedElement != null)
        {
            //postavljanje kliknutog elementa kao selektovanog
            selectedElement = clickedElement;
            slideView.getSelectedList().clear();
            slideView.setSelectedElement(clickedElement);
            ResizeDirection dir = slideView.findResizeDirection(clickedElement,e.getX(),e.getY()); //provera da li je klik na ivicu elementa
            if(dir != null)
            {
                //ukoliko jeste, prelazak u resize state
                MainFrame.getInstance().getStateManager().getResizeState().startResize(clickedElement,dir,e.getX(),e.getY());
                MainFrame.getInstance().getStateManager().setResizeState();
                slideView.repaint();
                return;
            }
            if(clickedElement == prevElement)
            {
                //ukoliko je ponovo kliknut već selektovan element, prelazi se u move state
                MainFrame.getInstance().getStateManager().setMoveState();
            }
            slideView.repaint();
            return;
        }
        //ukoliko ništa nije kliknuto, briše se selekcija
        selectedElement = null;
        slideView.setSelectedElement(null);
        slideView.getSelectedList().clear();
        //selektovanje pravougaonika za biranje više elemenata (trenutno ne radi)
        slideView.startSelectionRectangle(e.getPoint());
        slideView.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(!dragging) return;
        dragging = false;
        Rectangle selRect = slideView.getSelectionRectangle();
        slideView.setSelectionRectangle(null);
        if(selRect == null) return;
        slideView.setSelectedElement(null);
        slideView.getSelectedList().clear();
        for(GraffNode el : slide.getChildren())
        {
            if(el instanceof GraffSlideElement)
            {
                Rectangle elRect = new Rectangle((int) (((GraffSlideElement) el).getX()* slideView.getZoom()),
                        (int) (((GraffSlideElement) el).getY()* slideView.getZoom()),
                        (int) (((GraffSlideElement) el).getWidth()* slideView.getZoom()),
                        (int) (((GraffSlideElement) el).getHeight()* slideView.getZoom()));
                if(selRect.intersects(elRect))
                {
                    slideView.getSelectedList().add((GraffSlideElement) el);
                }
            }
        }
        slideView.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(!dragging) return;
        int x = Math.min(e.getX(), startPoint.x);
        int y = Math.min(e.getY(), startPoint.y);
        int w = Math.abs(startPoint.x - e.getX());
        int h = Math.abs(startPoint.y - e.getY());
        slideView.setSelectionRectangle(new Rectangle(x, y, w, h));
        slideView.repaint();

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
    @Override public void mouseWheelMoved(MouseWheelEvent e) {MainFrame.getInstance().getStateManager().setZoomState();}
}
