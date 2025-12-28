package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.serialization.Serializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

public class SaveTemplateAction extends AbstractGraffAction {

    public SaveTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/blueprintsave.png"));
        putValue(NAME, "Save As Template");
        putValue(SHORT_DESCRIPTION, "Save As Templatee");
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
            System.err.println("File " + "images/blueprintsave.png" + " not found");
        }
        return icon;
    }

    public Project getProject(GraffNode node) {
        Project project = null;
        if (node.getParent() instanceof Project) {
            project = (Project) node.getParent();
        } else if (node.getParent() instanceof Presentation) {
            project = (Project) node.getParent().getParent();
        } else if (node.getParent() instanceof Workspace) {
            project = (Project) node;
        }
        return project;
    }

    public void actionPerformed(ActionEvent e) {

        GraffNode node = MainFrame.getInstance().getGraffTree().getSelectedNode().getGraffNode();

        Project project = getProject(node);
        File baseDir = new File(System.getProperty("user.dir"));
        File saveDir = new File(baseDir, "/src/main/resources/templates");
        saveDir.mkdirs();

        File defaultDir = saveDir;
        if (!defaultDir.exists()) {
            defaultDir.mkdirs();
        }

        JFileChooser chooser = new JFileChooser(defaultDir);

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();

        if (!file.getName().endsWith(".json")) {
            file = new File(file.getAbsolutePath() + ".json");
        }

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");

        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);

        try {
            project.setFilePath(file);
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