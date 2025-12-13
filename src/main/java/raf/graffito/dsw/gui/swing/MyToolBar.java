package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ActionManager;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
       add(MainFrame.getInstance().getActionManager().getNewNodeAction());
       add(MainFrame.getInstance().getActionManager().getRemovalAction());
       add(MainFrame.getInstance().getActionManager().getRenameAction());
       add(MainFrame.getInstance().getActionManager().getExitAction());
       add(MainFrame.getInstance().getActionManager().getDeleteAction());
       add(MainFrame.getInstance().getActionManager().getRotateLeftAction());
       add(MainFrame.getInstance().getActionManager().getRotateRightAction());
    }
}
