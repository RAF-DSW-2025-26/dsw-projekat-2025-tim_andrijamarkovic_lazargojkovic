package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.model.ImageResource;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

public class AddImageAction extends AbstractGraffAction
{
    public AddImageAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/add-image.png")); // Postavljanje ikonice
        putValue(NAME, "Add Image"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Add Image"); // Tooltip
    }

    @Override
    public Icon loadIcon(String path)
    {
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
    public void actionPerformed(ActionEvent e)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
        if(chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            try
            {
                BufferedImage img = ImageIO.read(file);
                ImageResource resource = new ImageResource(file.getName(), img);
                MainFrame.getInstance().getImageRepository().addImage(resource);
            }
            catch (IOException ex)
            {
                String message = ApplicationFramework.getInstance().getGenerator().createMessage("Došlo je do greške pri učitavanju fajla!", Type.GRESKA, LocalDateTime.now());
                ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
                ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
            }
        }
    }
}
