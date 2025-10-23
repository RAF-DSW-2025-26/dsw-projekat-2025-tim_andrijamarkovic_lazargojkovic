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
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null);
        JPanel headerPanel = new JPanel(new BorderLayout(10,10));
        JLabel title = new JLabel("About Us", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 32));
        headerPanel.add(title, BorderLayout.CENTER);
        frame.add(headerPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        JPanel lazarPanel = new JPanel(new BorderLayout());
        JLabel imgLazar = new JLabel();
        imgLazar.setIcon(new ImageIcon(new ImageIcon("src/main/resources/images/lazar.png").getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)));
        imgLazar.setHorizontalAlignment(JLabel.CENTER);
        JLabel lblLazar = new JLabel("Lazar Gojković RN 52/2024", JLabel.CENTER);
        lazarPanel.add(imgLazar, BorderLayout.CENTER);
        lazarPanel.add(lblLazar, BorderLayout.NORTH);
        JPanel andrijaPanel = new JPanel(new BorderLayout());
        JLabel imgAndrija = new JLabel();
        imgAndrija.setIcon(new ImageIcon(new ImageIcon("src/main/resources/images/andrija.jpg").getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)));
        imgAndrija.setHorizontalAlignment(JLabel.CENTER);
        JLabel lblAndrija = new JLabel("Andrija Marković SI 65/2025", JLabel.CENTER);
        andrijaPanel.add(imgAndrija, BorderLayout.CENTER);
        andrijaPanel.add(lblAndrija, BorderLayout.NORTH);
        centerPanel.add(lazarPanel);
        centerPanel.add(andrijaPanel);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
