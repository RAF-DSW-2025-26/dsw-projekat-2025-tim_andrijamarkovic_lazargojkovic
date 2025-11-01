package raf.graffito.dsw.gui.swing.tree.view;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class GraffTreeCellRenderer extends DefaultTreeCellRenderer
{
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        URL imageURL = null;
        if(((GraffTreeItem)value).getGraffNode() instanceof Workspace)
        {
            imageURL = getClass().getResource("images/workspace.png");
        }
        else if(((GraffTreeItem)value).getGraffNode() instanceof Project)
        {
            imageURL = getClass().getResource("images/project.png");
        }
        else if(((GraffTreeItem)value).getGraffNode() instanceof Presentation)
        {
            imageURL = getClass().getResource("images/presentation.png");
        }
        else if(((GraffTreeItem)value).getGraffNode() instanceof Slide)
        {
            imageURL = getClass().getResource("images/slide.png");
        }
        Icon icon = null;
        if(imageURL != null)
        {
            icon = new ImageIcon(imageURL);
        }
        setIcon(icon);
        return this;
    }
}
