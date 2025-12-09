package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ISubscriberView;
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
    private StateManager stateManager;
    private List<GraffSlideElement> selected = new ArrayList<>();
    private Rectangle selectionRectangle;

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
    public void mouseClicked(MouseEvent e) {
        stateManager.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        stateManager.getCurrentState().mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        stateManager.getCurrentState().mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrentState().mouseWheelMoved(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
