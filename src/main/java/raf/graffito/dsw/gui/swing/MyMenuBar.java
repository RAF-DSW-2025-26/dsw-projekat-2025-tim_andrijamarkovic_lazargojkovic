package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRemovalAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAboutUs());
        add(fileMenu);
    }
}
