package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Workspace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

public class RemovalAction extends AbstractGraffAction {
    public RemovalAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/remove.png")); // Postavljanje ikonice
        putValue(NAME, "Remove"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Remove Node"); // Tooltip
    }

    private void removeNodeRecursive(GraffTreeItem item) {
        ArrayList<GraffTreeItem> children = new ArrayList<GraffTreeItem>();
        children.addAll(item.getChildren());
        for (GraffTreeItem child : children) {
            removeNodeRecursive(child);
        }

        MainFrame.getInstance().getGraffTree().removeNode(item);
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

        GraffTreeItem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        if (selected == null) {
            JOptionPane.showMessageDialog(null, "Nijedan čvor nije selektovan!", "Greška", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (selected.getGraffNode() instanceof Workspace) {
            JOptionPane.showMessageDialog(null, "Glavni Workspace se ne može ukloniti!", "Zabranjena akcija", JOptionPane.ERROR_MESSAGE);
            return;
        }
        removeNodeRecursive(selected);
    }
}