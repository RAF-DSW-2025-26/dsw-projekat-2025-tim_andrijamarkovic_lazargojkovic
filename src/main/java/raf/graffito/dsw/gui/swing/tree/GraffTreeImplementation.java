package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeImplementation implements GraffTree
{
    private GraffTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public GraffTreeView generateTree(Workspace workspace)
    {
        GraffTreeItem root = new GraffTreeItem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new GraffTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(GraffTreeItem parent)
    {
        if(!(parent.getGraffNode() instanceof GraffNodeComposite))
        {
            return;
        }
        GraffNode child = createChild(parent.getGraffNode());
        parent.add(new GraffTreeItem(child));
        ((GraffNodeComposite) parent.getGraffNode()).dodaj(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    private GraffNode createChild(GraffNode parent)
    {
        if(parent instanceof Workspace)
        {
            int nextNumber = Project.getProjectNumber();
            return new Project("Project" + nextNumber , "","", parent);
        }
        else if(parent instanceof Project)
        {
            int nextNumber = Presentation.getPresentationNumber();
            return new Presentation("Presentation" + nextNumber , parent);
        }
        else if(parent instanceof Presentation)
        {
            int nextNumber = Slide.getSlideNumber();
            return new Slide("Slide" + nextNumber , parent);
        }
        return null;
    }

    @Override
    public GraffTreeItem getSelectedNode()
    {
        return (GraffTreeItem) treeView.getLastSelectedPathComponent();
    }
}
