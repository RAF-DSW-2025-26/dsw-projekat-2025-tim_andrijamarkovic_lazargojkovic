package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.decorator.ColorDecorator;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
@Getter
public class PresentationView extends JTabbedPane{

    private HashMap<GraffTreeItem, Color> usedColors = new HashMap<>();

    public void showPresentationTabs(GraffTreeItem item)
    {
        Color color = null;
        if(!usedColors.containsKey(item)){
            color = chooseColor(item);
        }else{
            color = usedColors.get(item);
        }


        ColorDecorator decorated = new ColorDecorator(item.toString(),item.getGraffNode().getParent(),color);

        PresentationView tabbedPane = MainFrame.getInstance().getTabbedPane();

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

    private boolean isColorUsed(Color color) {
        for (Color c : usedColors.values()) {
            if (c.equals(color)) {
                return true;
            }
        }
        return false;
    }

    private Color chooseColor(GraffTreeItem item) {
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
                usedColors.put(item,color); // zapamti izabranu boju
                return color;
            }
        } while (true);
    }
}
