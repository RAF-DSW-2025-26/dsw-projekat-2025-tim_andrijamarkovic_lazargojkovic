package raf.graffito.dsw.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
@Setter
public class GraffTreeItem extends DefaultMutableTreeNode
{
    private GraffNode graffNode;

    public GraffTreeItem(GraffNode graffNode)
    {
        this.graffNode = graffNode;
    }

    @Override
    public String toString()
    {
        return graffNode.getName();
    }

    public void setName(String name)
    {
        this.graffNode.setName(name);
    }
}
