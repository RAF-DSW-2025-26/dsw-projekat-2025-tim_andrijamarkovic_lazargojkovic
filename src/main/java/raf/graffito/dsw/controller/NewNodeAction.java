package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDateTime;

public class NewNodeAction extends AbstractGraffAction {

    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
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
            System.err.println("File " + "images/new.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        LoggerFactory loggerFactory = new LoggerFactory();
        Logger cLogger = loggerFactory.createLogger("console");
        Logger fLogger = loggerFactory.createLogger("file");
        MessageGenerator messageGenerator = new MessageGenerator();
        messageGenerator.addSubscriber(cLogger);
        messageGenerator.addSubscriber(fLogger);

        if (selected == null) {
            String message = messageGenerator.createMessage("Nijedan čvor nije selektovan!", Type.UPOZORENJE, LocalDateTime.now());
            messageGenerator.notifySubscribers(message);
            messageGenerator.removeSubscriber(cLogger);
            messageGenerator.removeSubscriber(fLogger);
            return;
        }

        String[] availableTypes = selected.getAllowedChildTypes(selected.getGraffNode());

        if (availableTypes.length == 0) {
            String message = messageGenerator.createMessage("Ovaj čvor ne može da ima decu!", Type.OBAVESTENJE, LocalDateTime.now());
            messageGenerator.notifySubscribers(message);
            messageGenerator.removeSubscriber(cLogger);
            messageGenerator.removeSubscriber(fLogger);
            return;
        }

        // dijalog za izbor tipa deteta
        String choice = (String) JOptionPane.showInputDialog(
                MainFrame.getInstance(),
                "Izaberi tip novog elementa:",
                "Dodaj novi element",
                JOptionPane.PLAIN_MESSAGE,
                null,
                availableTypes,
                availableTypes[0]
        );

        if (choice != null) {
            MainFrame.getInstance().getGraffTree().addChild(selected, choice);
            messageGenerator.removeSubscriber(cLogger);
            messageGenerator.removeSubscriber(fLogger);
        } else {
            String message = messageGenerator.createMessage("Neuspešno kreiranje elementa!", Type.GRESKA, LocalDateTime.now());
            messageGenerator.notifySubscribers(message);
            messageGenerator.removeSubscriber(cLogger);
            messageGenerator.removeSubscriber(fLogger);
        }
    }
}
