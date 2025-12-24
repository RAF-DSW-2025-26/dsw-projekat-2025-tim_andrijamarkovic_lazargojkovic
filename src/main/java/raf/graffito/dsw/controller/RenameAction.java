package raf.graffito.dsw.controller;

import com.sun.tools.javac.Main;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.time.LocalDateTime;
//Akcija koja menja ime izabranih cvorova sa proverom duplikata
public class RenameAction extends AbstractGraffAction
{
    public RenameAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename Node");
    }
    @Override
    public Icon loadIcon(String path)
    {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path);
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File " + "images/rename.png" + " not found");
        }
        return icon;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GraffTreeItem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);

        if (selected == null)
        {
            String message = ApplicationFramework.getInstance().getGenerator().createMessage("Nijedan čvor nije selektovan!", Type.UPOZORENJE, LocalDateTime.now());
            ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
            ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
            return;
        }
        if(selected.getGraffNode() instanceof Project)
        {
            Project  project = (Project)selected.getGraffNode();
            JTextField nameField = new JTextField(selected.getGraffNode().getName());
            JTextField titleField = new JTextField(((Project) selected.getGraffNode()).getTitle());
            JTextField authorField = new JTextField(((Project) selected.getGraffNode()).getAuthor());
            JPanel panel = new JPanel(new GridLayout(3,3,10,10));
            panel.add(new JLabel("Novo ime:"));
            panel.add(nameField);
            panel.add(new JLabel("Novi naslov:"));
            panel.add(titleField);
            panel.add(new JLabel("Autor:"));
            panel.add(authorField);
            //Dijalog za menjanje parametra izabranog projekta
            int result = JOptionPane.showConfirmDialog(
                    MainFrame.getInstance(),
                    panel,
                    "Izmena projekta",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            if(result == JOptionPane.OK_OPTION)
            {
                String newName = nameField.getText().trim();
                if(selected.existsDuplicateName((GraffTreeItem) selected.getParent(),newName)){
                    String message = ApplicationFramework.getInstance().getGenerator().createMessage("Vec postoji cvor sa ovim imenom", Type.GRESKA, LocalDateTime.now());
                    ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                    return;
                }
                String newTitle = titleField.getText().trim();
                String newAuthor = authorField.getText().trim();
                if(newName.isEmpty() || newTitle.isEmpty() || newAuthor.isEmpty())
                {
                    String message = ApplicationFramework.getInstance().getGenerator().createMessage("Polja ne smeju ostajati prazna", Type.OBAVESTENJE, LocalDateTime.now());
                    ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                }
                else
                {
                    selected.getGraffNode().setName(newName);
                    String message = ApplicationFramework.getInstance().getGenerator().createMessage("Uspešno promenjen naziv čvora", Type.OBAVESTENJE, LocalDateTime.now());
                    ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                    ((Project) selected.getGraffNode()).setTitle(newTitle);
                    ((Project) selected.getGraffNode()).setAuthor(newAuthor);
                    ((Project) selected.getGraffNode()).notifySubscribers("nameChanged");
                    ((Project) selected.getGraffNode()).notifySubscribers("authorChanged");
                    project.setChanged(true);
                }
            }
        }
        else {
            String currentName = selected.getGraffNode().getName();
            //Dijalog za menjanje imena prezentacije
            String newName = JOptionPane.showInputDialog(
                    MainFrame.getInstance(),
                    "Unesite novo ime:",
                    currentName
            );

            if(!selected.existsDuplicateName((GraffTreeItem) selected.getParent(),newName)){
            if (newName != null && !newName.trim().isEmpty()) {
                selected.getGraffNode().setName(newName.trim());
                String message = ApplicationFramework.getInstance().getGenerator().createMessage("Uspešno promenjen naziv čvora", Type.OBAVESTENJE, LocalDateTime.now());
                ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                if(selected.getGraffNode() instanceof Presentation)
                {
                    ((Presentation) selected.getGraffNode()).addSubscriber(MainFrame.getInstance().getTabbedPane());
                    for(int i = 0; i < MainFrame.getInstance().getTabbedPane().getTabCount(); i++)
                    {
                        String tabTitle = MainFrame.getInstance().getTabbedPane().getTitleAt(i);
                        if(tabTitle.equals(currentName))
                        {
                            MainFrame.getInstance().getTabbedPane().setTitleAt(i, newName);
                            break;
                        }
                    }
                    ((Presentation) selected.getGraffNode()).notifySubscribers("nameChangedPresentation");
                }
            } else if (newName == null) {
                String message = ApplicationFramework.getInstance().getGenerator().createMessage("Nije unešeno novo ime", Type.UPOZORENJE, LocalDateTime.now());
                ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
            }
            }
            else{
                String message = ApplicationFramework.getInstance().getGenerator().createMessage("Vec postoji cvor sa ovim imenom", Type.GRESKA, LocalDateTime.now());
                ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                return;
            }
        }
        ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
    }
}
