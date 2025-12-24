package raf.graffito.dsw.controller;
import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.serialization.Serializer;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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

    public Project getProject(GraffNode node) {
        Project project = null;
        if(node.getParent() instanceof Project){
            project = (Project) node.getParent();
        }else if(node.getParent() instanceof Presentation){
            project = (Project) node.getParent().getParent();
        }else if(node.getParent() instanceof Workspace) {
            project = (Project) node;
        }
        return project;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffNode node = MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();
        Project project = getProject(node);

        if(project == null){
            return;
        }

        if (!project.isChanged()) return;

        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);

        try {
            if (project.getFilePath() == null) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
                    return;

                project.setFilePath(chooser.getSelectedFile());
            }

            Serializer.saveProject(project, project.getFilePath());
            project.setChanged(false);

        } catch (IOException ex) {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Greska pri cuvanju", Type.GRESKA, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);

            ex.printStackTrace();
        }
    }
}
