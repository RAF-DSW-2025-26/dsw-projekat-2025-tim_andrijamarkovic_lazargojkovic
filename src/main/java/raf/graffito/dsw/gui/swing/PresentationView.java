package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import raf.graffito.dsw.core.*;
import raf.graffito.dsw.decorator.ColorDecorator;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
//Pogled na individualne prezentacije unutar TabbedPane-a i azuriranje TabbedPane-a u skladu sa promenama nad prezentacijama
public class PresentationView extends JTabbedPane implements ISubscriberView {

    private HashMap<GraffTreeItem, Color> usedColors = new HashMap<>();//HashMapa koja sadrzi projekte i boje asocirane sa njima
    private Map<Slide,SlideView> slideViews = new HashMap<>();
    private SlideView activeSlideView;
    private Map<Presentation, JPanel> presentationPanels = new HashMap<>();

    public PresentationView()
    {
        //Metoda koja prati promene tabova
        addChangeListener(e ->
        {
            int selectedIndex = getSelectedIndex();
            if(selectedIndex != -1)
            {
                String title = getTitleAt(selectedIndex);
                onTabChanged(title);
            }
        });
    }

    //Otvara TabbedPane za izabrani projekat
    public void showPresentationTabs(GraffTreeItem item)
    {
        Color color = null;
        //Ukoliko izabrani projekat se prvi put otvara,otvara se dijalog za biranje boja
        if(!usedColors.containsKey(item)){
            color = chooseColor(item);
        }else{
            color = usedColors.get(item);
        }

        ColorDecorator decorated = new ColorDecorator(item.toString(),item.getGraffNode().getParent(),color);

        ProjectView projectView = MainFrame.getInstance().getProjectView();
        PresentationView tabbedPane = MainFrame.getInstance().getTabbedPane();

        tabbedPane.removeAll();
        //Dodeljuje odabranu boju svim tabovima projekta
        for (GraffTreeItem child : item.getChildren()) {
            if (child.getAllowedChildTypes(child.getGraffNode()).length==1) {
                JPanel panel = createPresentationPanel(child);
                tabbedPane.addTab(child.toString(), panel);
                int idx = tabbedPane.indexOfComponent(panel);
                tabbedPane.setBackgroundAt(idx, decorated.getColor());
                createSlideViews(child);
            }
        }
        projectView.update("nameChanged", (Project) item.getGraffNode());
        projectView.update("authorChanged", (Project) item.getGraffNode());
        projectView.update("nameChangedPresentation", (Project) item.getGraffNode());
    }

    private JPanel createPresentationPanel(GraffTreeItem presentationItem) {
        JPanel panel = new JPanel();
        Presentation p = (Presentation) presentationItem.getGraffNode();
        presentationPanels.put(p, panel);
        panel.setLayout(new BorderLayout());
        return panel;
    }
    //Funkcija koja za svaki slide kreira njemu SlideView-ove
    private void createSlideViews(GraffTreeItem presentationItem)
    {
        for(GraffTreeItem child : presentationItem.getChildren())
        {
            if(child.getGraffNode() instanceof Slide)
            {
                SlideView slideView = slideViews.get((Slide)child.getGraffNode());
                if(slideView==null) //Ukoliko neki Slide već ima svoj View, preskače se
                {
                    slideView = new SlideView();
                    slideViews.put(((Slide)child.getGraffNode()),slideView);
                    System.out.println("Kreiran View za: " + child.getGraffNode().getName());
                }
            }
        }
    }

    private boolean isColorUsed(Color color) {
        for (Color c : usedColors.values()) {
            if (c.equals(color)) {
                return true;
            }
        }
        return false;
    }
    //Metoda koja otvara dijalog za odabir boja
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
                usedColors.put(item,color); // Zapamti izabranu boju
                return color;
            }
        } while (true);
    }
    //Metoda koja na promenu taba azurira ime prezentacije prikazane u desnom panelu
    private void onTabChanged(String title)
    {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        projectView.getPresentationName().setText("Ime Prezentacije: " + title);
    }

    public void showSlide(Slide slide) {
        Presentation presentation = (Presentation) slide.getParent();
        JPanel panel = presentationPanels.get(presentation);
        // ukloni prethodni prikaz
        if(panel==null) return;

        panel.removeAll();

        // uzmi view iz mape
        SlideView view = slideViews.get(slide);
        if (view == null) {
            // ako nije postojao → kreiraj ga
            view = new SlideView();
            view.setSlide(slide);
            slideViews.put(slide, view);
        }
        view.setPreferredSize(new Dimension(800,600));
        panel.revalidate();
        // prikaži
        panel.add(view);

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void update(String message, Project project)
    {
        ProjectView projectView = MainFrame.getInstance().getProjectView();
        switch (message)
        {
            case "nameChangedPresentation":
                projectView.update("nameChangedPresentation", project);
                break;
        }
    }
}
