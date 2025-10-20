package raf.graffito.dsw.gui.swing;



import raf.graffito.dsw.controller.AboutUs;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ActionManager actionManager = new ActionManager();
        fileMenu.add(actionManager.getExitAction());
        fileMenu.add(actionManager.getAboutUs());
        add(fileMenu);
    }
}
