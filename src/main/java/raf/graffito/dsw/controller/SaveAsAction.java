package raf.graffito.dsw.controller;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
@Getter
@Setter

public class SaveAsAction extends AbstractGraffAction {
    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/saveas.png")); // Postavljanje ikonice
        putValue(NAME, "Save As"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Save As"); // Tooltip
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
            System.err.println("File " + "images/saveas.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Save As");
    }
}
