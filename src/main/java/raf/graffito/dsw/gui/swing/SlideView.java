package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.commands.concrete.DeleteElementCommand;
import raf.graffito.dsw.commands.concrete.RotateCommand;
import raf.graffito.dsw.core.*;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.*;
import raf.graffito.dsw.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlideView extends JPanel implements ISubscriberView, MouseListener, MouseMotionListener, MouseWheelListener {

    private Slide slide;
    private GraffSlideElement selectedElement;
    private List<GraffSlideElement> selectedList = new ArrayList<>();
    private Point selectionStart;
    private Rectangle selectionRectangle;
    private double zoom = 1.0;
    private AffineTransform transform = new AffineTransform();

    private static final int EDGE = 6;
    private final double MIN_ZOOM = 0.2;
    private final double MAX_ZOOM = 3.0;

    public SlideView() {
        setMinimumSize(new Dimension(400, 400));
        setSize(400,400);
        setLayout(new BorderLayout());
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setBackground(Color.WHITE);
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl C"),"copy");
        getActionMap().put("copy", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                copySelected();
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl V"),"paste");
        getActionMap().put("paste", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                paste();
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl Z"),"undo");
        getActionMap().put("undo", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.getInstance().getCommandManager().undo();
                repaint();
            }
        });
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl Y"),"redo");
        getActionMap().put("redo", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.getInstance().getCommandManager().redo();
                repaint();
            }
        });
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
        transform = new AffineTransform();
        transform.scale(zoom, zoom);
        g2d.transform(transform);
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
        g2d.dispose();
        if(selectionRectangle != null)
        {
            Graphics2D g2ds = (Graphics2D) g;
            g2ds.setColor(new Color(0,120,215,80));
            g2ds.fill(selectionRectangle);
            g2ds.setColor(Color.BLUE);
            g2ds.draw(selectionRectangle);
            g2ds.dispose();
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
        // odredjivanje da li je miš u blizini ivice (6 piksela)
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

        return ResizeDirection.NONE; //ukoliko nije nijedan, vraća da miš nije u blizini ivica elementa
    }

    public Point inverseTransform(MouseEvent e)
    {
        try
        {
            AffineTransform inverse = transform.createInverse();
            Point2D point = inverse.transform(new Point2D.Double(e.getX(), e.getY()), null);
            return new Point((int) point.getX(), (int) point.getY());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void deleteSelectedElement()
    {
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
        if(selectedElement != null)
        {
            Command cmd = new DeleteElementCommand(getSlideForView(this), selectedElement);
            MainFrame.getInstance().getCommandManager().executeCommand(cmd);
            selectedElement = null;
            repaint();
        }
        else
        {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Nijedan element nije selektovan!", Type.OBAVESTENJE, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
        }
    }

    public void rotateSelected(int direction)
    {
        if(selectedElement==null) return;
        double oldAngle = selectedElement.getRotationAngle();
        double newAngle = (oldAngle + Math.toRadians(selectedElement.getRotateRatio()*direction));
        Command cmd = new RotateCommand(selectedElement,oldAngle,newAngle);
        MainFrame.getInstance().getCommandManager().executeCommand(cmd);
        repaint();
    }

    public void startSelectionRectangle(Point p)
    {
        selectionStart = p;
        selectionRectangle = new Rectangle(p);
    }

    public void copySelected()
    {
        if(selectedElement==null) return;
        SlideClipboard.copy(selectedElement);
    }

    public void paste()
    {
        if(selectedElement==null) return;
        GraffSlideElement newElement = SlideClipboard.paste(getSlideForView(this));
        if(newElement==null) return;
        getSlideForView(this).getChildren().add(newElement);
        selectedElement = null;
        repaint();
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
