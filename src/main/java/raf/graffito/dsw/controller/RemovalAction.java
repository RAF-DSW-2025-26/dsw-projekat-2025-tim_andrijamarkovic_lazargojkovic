package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.ProjectView;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDateTime;
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
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
        if (selected == null) {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Nijedan čvor nije selektovan!", Type.UPOZORENJE, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
            return;
        }

        if (selected.getGraffNode() instanceof Workspace) {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Workspace ne sme biti obrisan!", Type.UPOZORENJE, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
            return;
        }
        if(selected.getGraffNode() instanceof Presentation)
        {
            Project project = (Project) selected.getGraffNode().getParent();
            project.notifySubscribers("presentationRemoved");
        }
        removeNodeRecursive(selected);

        if(selected.getGraffNode() instanceof Project){
            MainFrame.getInstance().getTabbedPane().removeAll();
        }
    }
}