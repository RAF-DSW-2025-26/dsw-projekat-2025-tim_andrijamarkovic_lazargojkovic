package raf.graffito.dsw.state.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.commands.concrete.AddElementCommand;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.SlideLogo;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.time.LocalDateTime;

public class AddLogoState implements State
{

    private Slide slide;
    private SlideView slideView;

    //na klik miša dok je u state-u kreira novi logo element
    @Override
    public void mouseClicked(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e); // transformiše trenutne koordinate miša u koordinate prikaza u trenutnom stanju (zbog zooma)
        if(slideView.canAddElement())
        {
            SlideLogo logo = new SlideLogo("Logo" + slide.getChildren().size(), slide, p.x, p.y, 100, 100, 0);
            Command cmd = new AddElementCommand(slide, logo);
            MainFrame.getInstance().getCommandManager().executeCommand(cmd);
            slideView.repaint();
            MainFrame.getInstance().getStateManager().setSelectState();
            if (slide.getParent() instanceof Project) {
                Project project = (Project) slide.getParent();
                project.setChanged(true);
            } else if (slide.getParent() instanceof Presentation) {
                Project project = (Project) slide.getParent().getParent();
                project.setChanged(true);
            }
        }
        else
        {
            Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
            Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
            ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Nema dovoljno mesta na slajdu za dodavanje novih elemenata!", Type.OBAVESTENJE, LocalDateTime.now());
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
