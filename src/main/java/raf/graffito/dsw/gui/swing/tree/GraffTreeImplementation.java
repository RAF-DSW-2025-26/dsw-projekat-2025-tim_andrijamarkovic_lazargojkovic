package raf.graffito.dsw.gui.swing.tree;

import lombok.Getter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;
import raf.graffito.dsw.repository.GraffRepositoryImpl;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;
import java.util.List;
@Getter
//Klasa koja sadrzi konkretne metode definisane u interfejsu GraffTree
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
    //Metoda koja kreira dete izabranom cvoru koristeci Factory Method
    public GraffNode createChild(GraffNode parent, String childType) {
        childType = childType.toLowerCase();

        if (parent instanceof Workspace) {
            switch (childType) {
                case "project":
                    return new GraffRepositoryImpl().projectFactory(parent);
                case "presentation":
                    return new GraffRepositoryImpl().presentationFactory(parent);
                case "slide":
                    return new GraffRepositoryImpl().slideFactory(parent);
            }
        }
        else if (parent instanceof Project) {
            switch (childType) {
                case "presentation":
                    return new GraffRepositoryImpl().presentationFactory(parent);
                case "slide":
                    return new GraffRepositoryImpl().slideFactory(parent);
            }
        }
        else if (parent instanceof Presentation) {
            if (childType.equals("slide")) {
                return new GraffRepositoryImpl().slideFactory(parent);
            }
        }

        return null;
    }

    //Metoda koja dodaje dete izabranom cvoru i u azurira JTree da prikazuje i njega
    @Override
    public void addChild(GraffTreeItem parent,String childType)
    {
        if(!(parent.getGraffNode() instanceof GraffNodeComposite))
        {
            return;
        }
        GraffNode child = createChild(parent.getGraffNode(),childType);
        parent.add(new GraffTreeItem(child));
        GraffNodeComposite parentNode = (GraffNodeComposite) parent.getGraffNode();
        parentNode.getChildren().add(child);
        child.setParent(parentNode);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        Project project = getProjectFromNode(parent.getGraffNode());
        project.setChanged(true);
    }

    @Override
    public void removeNode(GraffTreeItem item) {
        DefaultTreeModel model = (DefaultTreeModel) getTreeModel();
        model.removeNodeFromParent(item);
    }

    @Override
    public GraffTreeItem getSelectedNode()
    {
        return (GraffTreeItem) treeView.getLastSelectedPathComponent();
    }

    private Project getProjectFromNode(GraffNode node) {
        while (node != null && !(node instanceof Project)) {
            node = node.getParent();
        }
        return (Project) node;
    }

    public void addExistingNode(GraffNode node) {
        GraffTreeItem root = (GraffTreeItem) treeModel.getRoot();
        root.add(new GraffTreeItem(node));
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void refresh(){
        ((DefaultTreeModel)getTreeModel()).reload();
    }
}
