package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ActionManager actionManager = new ActionManager();
        fileMenu.add(actionManager.getNewNodeAction());
        fileMenu.add(actionManager.getRemovalAction());
        fileMenu.add(actionManager.getRenameAction());
        fileMenu.add(actionManager.getExitAction());
        fileMenu.add(actionManager.getAboutUs());
        add(fileMenu);
    }
}
