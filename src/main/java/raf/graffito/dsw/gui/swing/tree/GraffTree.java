package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;
import raf.graffito.dsw.model.Workspace;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
//Interface koji generise JTree i upravlja cvorovima
public interface GraffTree
{
    GraffTreeView generateTree(Workspace workspace);
    void addChild(GraffTreeItem child,String childType);
    GraffTreeItem getSelectedNode();
    void removeNode(GraffTreeItem child);
}
