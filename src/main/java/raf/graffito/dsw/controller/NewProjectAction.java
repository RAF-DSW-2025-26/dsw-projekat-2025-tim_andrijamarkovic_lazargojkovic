package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class NewProjectAction extends AbstractGraffAction
{

    public NewProjectAction()
    {
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    @Override
    public Icon loadIcon(String path)
    {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path);
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + "images/new.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GraffTreeItem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().addChild(selected);
    }
}
