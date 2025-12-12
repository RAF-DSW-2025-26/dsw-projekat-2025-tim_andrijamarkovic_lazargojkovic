package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.SlideLabel;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.time.LocalDateTime;

public class AddTextState implements State
{
    private Slide slide;
    private SlideView slideView;

    @Override
    public void mouseClicked(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e);
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
        String text = JOptionPane.showInputDialog(slideView, "Unesite tekst:");
        if(text != null)
        {
            SlideLabel label = new SlideLabel("Label" + slide.getChildren().size(), slide, p.x, p.y, 150, 40, 0,0, text);
            slide.getChildren().add(label);
            slideView.repaint();
            MainFrame.getInstance().getStateManager().setSelectState();
        }
        else
        {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Polje ne sme ostati prazno!", Type.OBAVESTENJE, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
