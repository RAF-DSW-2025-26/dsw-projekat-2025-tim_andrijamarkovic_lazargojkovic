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
       add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
       add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
       JRadioButton normalRB = new JRadioButton(MainFrame.getInstance().getActionManager().getNormalModeAction());
       JRadioButton smallRB = new JRadioButton(MainFrame.getInstance().getActionManager().getSmallModeAction());
       JRadioButton fullscreenRB = new JRadioButton(MainFrame.getInstance().getActionManager().getFullscreenModeAction());
       ButtonGroup group = new ButtonGroup();
       group.add(normalRB);
       group.add(smallRB);
       group.add(fullscreenRB);
       normalRB.setSelected(true);
       add(normalRB);
       add(smallRB);
       add(fullscreenRB);
    }
}
