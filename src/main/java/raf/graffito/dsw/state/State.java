package raf.graffito.dsw.state;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State
{
    void mousePressed(MouseEvent e);
    void mouseReleased(MouseEvent e);
    void mouseClicked(MouseEvent e);
    void mouseMoved(MouseEvent e);
    void mouseDragged(MouseEvent e);
    void mouseWheelMoved(MouseWheelEvent e);
}
