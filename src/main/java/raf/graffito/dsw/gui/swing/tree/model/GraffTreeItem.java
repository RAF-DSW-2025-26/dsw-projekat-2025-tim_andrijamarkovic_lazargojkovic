package raf.graffito.dsw.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Workspace;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GraffTreeItem extends DefaultMutableTreeNode
{
    private GraffNode graffNode;

    public GraffTreeItem(GraffNode graffNode)
    {
        this.graffNode = graffNode;
    }

    public String getName()
    {
        return graffNode.getName();
    }

    public String[] getAllowedChildTypes(GraffNode parent) {

        if (parent instanceof Workspace) {
            return new String[] { "project" };
        }
        else if (parent instanceof Project) {
            return new String[] { "presentation", "slide" };
        }
        else if (parent instanceof Presentation) {
            return new String[] { "slide" };
        }
        return new String[0];
    }

    public void setName(String name)
    {
        this.graffNode.setName(name);
    }

    public List<GraffTreeItem> getChildren() {
        List<GraffTreeItem> children = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            children.add((GraffTreeItem) getChildAt(i));
        }
        return children;
    }
}
