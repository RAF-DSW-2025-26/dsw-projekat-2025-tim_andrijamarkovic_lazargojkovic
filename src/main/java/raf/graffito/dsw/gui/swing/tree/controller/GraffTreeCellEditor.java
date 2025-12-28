package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.decorator.ColorDecorator;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.PresentationView;
import raf.graffito.dsw.gui.swing.SlideView;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

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
import java.util.List;

//Ova klasa sluzi da se za otvoreni projekat pokrene logika otvaranja TabbedPane-a
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
    //Metoda koja na dupli klik otvara selector boje i prikazuje TabbedPane
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
                        MainFrame.getInstance().getSplitPane().setLeftComponent(MainFrame.getInstance().getTabbedPane());
                        MainFrame.getInstance().getTabbedPane().showPresentationTabs(item);
                        MainFrame.getInstance().getProjectView().subscribe((Project) item.getGraffNode());
                    }
                    else if (item.getGraffNode() instanceof Slide) {
                        openSlide((Slide) item.getGraffNode());
                        MainFrame.getInstance().getProjectView().getSlideActionView().setVisible(true);
                    }
                }
                return false;
            }
        }
        return false;
    }
    public void openSlide(Slide slide) {

        GraffNode parent = slide.getParent();
        if (slide.getParent() == null) return;

        if(slide.getParent() instanceof Project) {
            if(MainFrame.getInstance().getTabbedPane().getSlideViews().containsKey(slide)){
                MainFrame.getInstance().getSplitPane().setLeftComponent(null);
                MainFrame.getInstance().getSplitPane().setLeftComponent(MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide));
                MainFrame.getInstance().getSplitPane().getLeftComponent().setMinimumSize(new Dimension(500,500));
            }
            else{
                SlideView slideView = new SlideView();
                MainFrame.getInstance().getTabbedPane().getSlideViews().put(slide, slideView);
                MainFrame.getInstance().getSplitPane().setLeftComponent(null);
                MainFrame.getInstance().getSplitPane().setLeftComponent(MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide));
                MainFrame.getInstance().getSplitPane().getLeftComponent().setMinimumSize(new Dimension(500,500));
            }
            return;
        }

        int tabIndex = findPresentationTab(parent.getName());

        // ako presentation tab nije otvoren → otvori ga
        if (tabIndex == -1) {
            MainFrame.getInstance().getTabbedPane().addTab(parent.getName(), MainFrame.getInstance().getTabbedPane());
            tabIndex = MainFrame.getInstance().getTabbedPane().getTabCount() - 1;
        }


        MainFrame.getInstance().getTabbedPane().getComponentAt(tabIndex);

        MainFrame.getInstance().getProjectView().update("slideChanged",(Project) parent.getParent());

        MainFrame.getInstance().getTabbedPane().showSlide(slide);

        MainFrame.getInstance().getTabbedPane().setSelectedIndex(tabIndex);

        MainFrame.getInstance().getTabbedPane().setActiveSlideView(MainFrame.getInstance().getTabbedPane().getSlideViews().get(slide));
    }
    private int findPresentationTab(String string) {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < MainFrame.getInstance().getTabbedPane().getTabCount(); i++) {
            titles.add(MainFrame.getInstance().getTabbedPane().getTitleAt(i));
        }
        for(int i = 0 ;i<titles.size();i++){
            if(titles.get(i).equals(string)){
                return i;
            }
        }
        return -1;
    }
}
