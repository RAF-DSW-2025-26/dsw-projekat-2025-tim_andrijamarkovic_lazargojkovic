package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import static raf.graffito.dsw.core.Type.OBAVESTENJE;
@Getter
@Setter
public class MainFrame extends JFrame {

    // Buduća polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private PresentationView tabbedPane;
    private JMenuBar menu;
    private JToolBar toolBar;
    private GraffTree graffTree;
    private ProjectView projectView;


    public MainFrame() {
        instance = this;
        initialize();
    }
    public static MainFrame instance;
    public static MainFrame getInstance() {

        if (instance == null) {
            instance = new MainFrame();

        }
        return instance;
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        actionManager = new ActionManager();

        menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora

        //Kreiranje JTree-a
        graffTree = new GraffTreeImplementation();
        JTree workspace = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        workspace.setDragEnabled(true);
        workspace.setDropMode(DropMode.ON_OR_INSERT);

        //Kreiranje panela koji sadrzi u sebi prikaz JTree-a, TabbedPane-a i RightPanel-a
        JPanel desktop = new JPanel();
        JScrollPane scroll=new JScrollPane(workspace);
        scroll.setMinimumSize(new Dimension(200,150));

        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(200);
        split.setOneTouchExpandable(true);

        projectView = new ProjectView();
        tabbedPane = new PresentationView();

        JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        split1.setDividerLocation(500);
        split1.setOneTouchExpandable(true);
        split1.setLeftComponent(tabbedPane);
        split1.setRightComponent(projectView);

        split.setLeftComponent(scroll);
        split.setRightComponent(split1);
    }
}
