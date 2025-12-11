package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class MoveAction extends AbstractGraffAction
{
    public MoveAction() {
        putValue(SMALL_ICON, loadIcon("/images/move.png")); // Postavljanje ikonice
        putValue(NAME, "Move"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Move"); // Tooltip
    }
    @Override
    public Icon loadIcon(String path) {
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
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getStateManager().setMoveState();
    }
}
