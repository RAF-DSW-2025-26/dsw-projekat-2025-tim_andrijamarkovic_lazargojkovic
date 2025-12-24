package raf.graffito.dsw.controller;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.serialization.Serializer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

@Getter
@Setter
public class OpenAction extends AbstractGraffAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/open.png")); // Postavljanje ikonice
        putValue(NAME, "Open"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Open"); // Tooltip
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
            System.err.println("File " + "images/open.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;


        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);

        try {
            Project project = Serializer.loadProjectFromJson(chooser.getSelectedFile());
            project.setFilePath(chooser.getSelectedFile());
            project.setChanged(false);

            Workspace workspace = ApplicationFramework.getInstance().getGraffRepository().getWorkspace();
            workspace.dodaj(project);

        } catch (IOException ex) {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Greska pri otvaranju", Type.GRESKA, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);

            ex.printStackTrace();
        }
    }
}
