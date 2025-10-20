package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
       ActionManager actionManager = new ActionManager();
       add(actionManager.getExitAction());
    }
}
