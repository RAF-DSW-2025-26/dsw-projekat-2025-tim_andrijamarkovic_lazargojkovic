package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class AboutUs extends AbstractGraffAction{
    public AboutUs(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(NAME, "About Us");
        putValue(SMALL_ICON, loadIcon("/images/about.png"));
        putValue(SHORT_DESCRIPTION, "About us");
    }


    @Override
    public Icon loadIcon(String path) {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path);
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + "images/about.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("About us");
        frame.setLayout(new GridLayout(3, 2));

        frame.setLocationRelativeTo(null);

        JLabel imgLazar = new JLabel();
        JLabel imgAndrija = new JLabel();
        imgLazar.setIcon(new ImageIcon(new ImageIcon("images/lazar.png").getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)));
        imgAndrija.setIcon(new ImageIcon(new ImageIcon("images/andrija.png").getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)));

        frame.add(imgLazar);
        frame.add(imgAndrija);
        frame.add(new JLabel("Lazar Gojkovic"));
        frame.add(new JLabel("Andrija Markovic"));
        frame.add(new JLabel("RN 52/2024"));
        frame.add(new JLabel("SI 65/2025"));

        frame.setSize(1200, 700);
        frame.setVisible(true);
    }
}
