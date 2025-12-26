package raf.graffito.dsw.state.concrete;

import lombok.Setter;
import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.commands.concrete.AddElementCommand;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.*;
import raf.graffito.dsw.state.State;
import raf.graffito.dsw.state.StateManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddImageState implements State
{
    private Slide slide = MainFrame.getInstance().getSlideView().getSlide();
    private SlideView slideView = MainFrame.getInstance().getSlideView();
    @Setter
    private ImageResource selectedImage;


    @Override
    public void mouseClicked(MouseEvent e)
    {
        slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        Point p = slideView.inverseTransform(e);
        SlidePicture picture = new SlidePicture("Picture" + slide.getChildren().size(),slide,p.x,p.y,200,150,0,selectedImage.getImage(),selectedImage.getPath());
        Command cmd = new AddElementCommand(slide,picture);
        MainFrame.getInstance().getCommandManager().executeCommand(cmd);
        slideView.repaint();
        MainFrame.getInstance().getStateManager().setSelectState();
        if(slide.getParent() instanceof Project){
            Project project = (Project)slide.getParent();
            project.setChanged(true);
        }else if(slide.getParent() instanceof Presentation){
            Project project  = (Project)slide.getParent().getParent();
            project.setChanged(true);
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseWheelMoved(MouseWheelEvent e) {}
}
