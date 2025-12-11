package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ISubscriberView;
import raf.graffito.dsw.core.ResizeDirection;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.*;
import raf.graffito.dsw.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlideView extends JPanel implements ISubscriberView, MouseListener, MouseMotionListener, MouseWheelListener {

    private Slide slide;
    private GraffSlideElement selectedElement;
    private List<GraffSlideElement> selectedList = new ArrayList<>();
    private Rectangle selectionRectangle;
    private static final int EDGE = 6;

    public SlideView() {
        setMinimumSize(new Dimension(400, 400));
        setSize(400,400);
        setLayout(new BorderLayout());
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setBackground(Color.WHITE);
    }

    @Override
    public void update(String message, Project project) {

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        slide = getSlideForView(this);
        for(GraffNode element : slide.getChildren())
        {
            if(element instanceof GraffSlideElement)
            {
                ((GraffSlideElement) element).draw(g2d);
            }
            if(selectedElement != null)
            {
                Stroke old = g2d.getStroke(); // Stroke crta outlines objekta, ovo čuva stari stroke (da ne bi ostali elementi bili iscrtanni)
                g2d.setStroke(new BasicStroke(2));
                g2d.setColor(Color.BLACK);
                g2d.drawRect(selectedElement.getX(), selectedElement.getY(), selectedElement.getWidth(), selectedElement.getHeight()); //crtanmje pravougaonika oko elementa
                g2d.setStroke(old);
            }
        }
    }

    private Slide getSlideForView(SlideView slideView)
    {
        for(Map.Entry<Slide,SlideView> entry : MainFrame.getInstance().getTabbedPane().getSlideViews().entrySet())
        {
            if(entry.getValue() == slideView)
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public ResizeDirection findResizeDirection(GraffSlideElement slideElement, int mx, int my)
    {
        int x = slideElement.getX();
        int y = slideElement.getY();
        int w = slideElement.getWidth();
        int h = slideElement.getHeight();

        boolean top = Math.abs(my-y) <= EDGE;
        boolean bottom = Math.abs(my-(y+h)) <= EDGE;
        boolean left = Math.abs(mx-x) <= EDGE;
        boolean right = Math.abs(mx-(x+w)) <= EDGE;
        //Na osnovu detektovane pozicije miša odredjuje u kom smeru će da se menja veličina
        if(top) return ResizeDirection.NORTH;
        if(bottom) return ResizeDirection.SOUTH;
        if(left) return ResizeDirection.WEST;
        if(right) return ResizeDirection.EAST;
        if(top && left) return ResizeDirection.NORTH_WEST;
        if(top && right) return ResizeDirection.NORTH_EAST;
        if(bottom && left) return ResizeDirection.SOUTH_WEST;
        if(bottom && right) return ResizeDirection.SOUTH_EAST;

        return ResizeDirection.NONE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getStateManager().getCurrentState().mouseWheelMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
