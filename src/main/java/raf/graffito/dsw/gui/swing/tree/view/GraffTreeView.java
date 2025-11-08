package raf.graffito.dsw.gui.swing.tree.view;

import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
//Klasa koja kreira JTree i dodaje mu funkcionalnosti iz nasih klasa
public class GraffTreeView extends JTree
{
    public GraffTreeView(DefaultTreeModel model)
    {
        setModel(model);
        GraffTreeCellRenderer renderer = new GraffTreeCellRenderer();
        addTreeSelectionListener(new GraffTreeSelectionListener());
        setCellRenderer(renderer);
        setCellEditor(new GraffTreeCellEditor(this, renderer));
        setCellRenderer(renderer);
        setEditable(true);
    }
}
