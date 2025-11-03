package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
       ActionManager actionManager = new ActionManager();
       add(actionManager.getNewNodeAction());
       add(actionManager.getRemovalAction());
       add(actionManager.getRenameAction());
       add(actionManager.getExitAction());
    }
}
