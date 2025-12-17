package raf.graffito.dsw.controller;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class SaveAction extends AbstractGraffAction{

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/save.png")); // Postavljanje ikonice
        putValue(NAME, "Save"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Save"); // Tooltip
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
            System.err.println("File " + "images/save.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save");
    }
}
