package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.decorator.ColorDecorator;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EventObject;

public class GraffTreeCellEditor extends DefaultTreeCellEditor implements ActionListener
{
    private Object clickedOn = null;
    private JTextField edit = null;

    public GraffTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer)
    {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object node, boolean isSelected, boolean isExpanded, boolean isLeaf, int index)
    {
        clickedOn = node;
        edit = new JTextField(node.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject e)
    {
        if(e instanceof MouseEvent)
        {
            if(((MouseEvent)e).getClickCount() == 2)
            {
                JTree tree = (JTree) e.getSource();
                Object selected = tree.getLastSelectedPathComponent();

                if (selected instanceof GraffTreeItem) {
                    GraffTreeItem item = (GraffTreeItem) selected;

                    if (item.getGraffNode() instanceof Project) {
                        MainFrame.getInstance().getTabbedPane().showPresentationTabs(item);
                        MainFrame.getInstance().getProjectView().subscribe((Project) item.getGraffNode());
                    }
                }
                return false;
            }
        }
        return false;
    }
}
