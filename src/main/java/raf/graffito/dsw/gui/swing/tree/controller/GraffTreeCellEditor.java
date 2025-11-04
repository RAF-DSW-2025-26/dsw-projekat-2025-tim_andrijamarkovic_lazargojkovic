package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.decorator.ColorDecorator;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.LoggerFactory;

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
    private ArrayList<Color> usedColors = new ArrayList<>();

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

                    if (item.getAllowedChildTypes(item.getGraffNode()).length==2) {
                        showPresentationTabs(item);
                    }
                }
                return false;
            }
        }
        return false;
    }

    public void showPresentationTabs(GraffTreeItem item)
    {
        Color color = chooseColor();
        if(color==null){
            return;
        }

        ColorDecorator decorated = new ColorDecorator(item.toString(),item.getGraffNode().getParent(),color);

        JTabbedPane tabbedPane = MainFrame.getInstance().getTabbedPane();

        tabbedPane.removeAll();

        for (GraffTreeItem child : item.getChildren()) {
            if (child.getAllowedChildTypes(child.getGraffNode()).length==1) {
                JPanel panel = createPresentationPanel(child);
                tabbedPane.addTab(child.toString(), panel);

                int idx = tabbedPane.indexOfComponent(panel);
                tabbedPane.setBackgroundAt(idx, decorated.getColor());
            }
        }
    }
    private JPanel createPresentationPanel(GraffTreeItem presentationItem) {
        JPanel panel = new JPanel(new BorderLayout());
        return panel;
    }

    public void actionPerformed(ActionEvent e)
    {
    }

    private boolean isColorUsed(Color color) {
        for (Color c : usedColors) {
            if (c.equals(color)) {
                return true;
            }
        }
        return false;
    }

    private Color chooseColor() {
        Color color;
        Logger cLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("console");
        Logger fLogger = ApplicationFramework.getInstance().getLoggerFactory().createLogger("file");
        ApplicationFramework.getInstance().getGenerator().addSubscriber(cLogger);
        ApplicationFramework.getInstance().getGenerator().addSubscriber(fLogger);
        do {
            color = JColorChooser.showDialog(MainFrame.getInstance(), "Izaberi boju za tab", Color.LIGHT_GRAY);
            if (color == null) return null; // Cancel

            if (isColorUsed(color)) {
                String message = ApplicationFramework.getInstance().getGenerator().createMessage("Ta boja je već iskorišćena!", Type.OBAVESTENJE, LocalDateTime.now());
                ApplicationFramework.getInstance().getGenerator().notifySubscribers(message);
                ApplicationFramework.getInstance().getGenerator().removeSubscriber(cLogger);
                ApplicationFramework.getInstance().getGenerator().removeSubscriber(fLogger);
            } else {
                usedColors.add(color); // zapamti izabranu boju
                return color;
            }
        } while (true);
    }
}
