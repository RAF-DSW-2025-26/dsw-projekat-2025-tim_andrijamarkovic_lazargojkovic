package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
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

public class OpenTemplateAction extends AbstractGraffAction {

    public OpenTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/blueprintopen.png"));
        putValue(NAME, "Open Template");
        putValue(SHORT_DESCRIPTION, "Load a template");
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
            System.err.println("File " + "images/blueprintopen.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // biranje fajla samo iz jednog templates direktorijuma
        File baseDir = new File(System.getProperty("user.dir"));
        File openDir = new File(baseDir, "/src/main/resources/templates");

        JFileChooser chooser = new JFileChooser(openDir);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;

        File selectedFile = chooser.getSelectedFile().getAbsoluteFile();

        if (!selectedFile.getParentFile().equals(openDir)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Dozvoljeno je učitavanje samo iz foldera:\n" + openDir.getPath(),
                    "Neispravna lokacija",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");

        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);

        try {
            Project project = Serializer.loadProjectFromJson(selectedFile);
            project.setFilePath(selectedFile);
            project.setChanged(false);

            Workspace workspace = ApplicationFramework
                    .getInstance()
                    .getGraffRepository()
                    .getWorkspace();

            project.setParent(workspace);
            workspace.dodaj(project);

            GraffTreeImplementation tree = (GraffTreeImplementation) MainFrame.getInstance().getGraffTree();

            tree.addExistingNode(project);
            tree.selectNode(project);

        } catch (IOException ex) {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Greska pri otvaranju", Type.GRESKA, LocalDateTime.now());

            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);

            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);

            ex.printStackTrace();
        }
    }

}
