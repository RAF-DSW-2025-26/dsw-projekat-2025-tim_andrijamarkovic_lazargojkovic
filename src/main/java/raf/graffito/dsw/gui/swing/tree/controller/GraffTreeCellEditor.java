package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
            if(((MouseEvent)e).getClickCount() == 3)
            {
                return true;
            }
        }
        return false;
    }

    public void actionPerformed(ActionEvent e)
    {
        if(!(clickedOn instanceof GraffTreeItem))
        {
            return;
        }
        GraffTreeItem item = (GraffTreeItem)clickedOn;
        item.setName(e.getActionCommand());
    }
}
