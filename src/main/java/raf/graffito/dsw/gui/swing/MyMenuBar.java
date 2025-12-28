package raf.graffito.dsw.gui.swing;



import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        add(fileMenu);
        JMenu nodeMenu = new JMenu("Node");
        nodeMenu.setMnemonic(KeyEvent.VK_N);
        nodeMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        nodeMenu.add(MainFrame.getInstance().getActionManager().getRenameAction());
        nodeMenu.add(MainFrame.getInstance().getActionManager().getRemovalAction());
        add(nodeMenu);
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getAddTextAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAddLogoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAddImageAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getSelectAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getMoveAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRotateLeftAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRotateRightAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(editMenu);
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        aboutMenu.add(MainFrame.getInstance().getActionManager().getAboutUs());
        add(aboutMenu);
    }
}
