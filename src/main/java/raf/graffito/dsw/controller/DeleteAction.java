package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class DeleteAction extends AbstractGraffAction
{
    public DeleteAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/delete.png")); // Postavljanje ikonice
        putValue(NAME, "Delete"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Delete"); // Tooltip
    }

    @Override
    public Icon loadIcon(String path)
    {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path); // URL služi za pronalaženje resursa unutar JAR fajla ili klase
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage(); // Napravimo Image iz ImageIcon
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + "images/exit.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Slide slide = (Slide) MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        SlideView slideView = MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide);
        slideView.deleteSelectedElement();
        if(slide.getParent() instanceof Project){
            Project p = (Project)slide.getParent();
            p.setChanged(true);
        }else if(slide.getParent() instanceof Presentation){
            Project p  = (Project)slide.getParent().getParent();
            p.setChanged(true);
        }
    }
}
