package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.Logger;
import raf.graffito.dsw.core.Type;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.model.ConsoleLogger;
import raf.graffito.dsw.model.FileLogger;
import raf.graffito.dsw.model.LoggerFactory;
import raf.graffito.dsw.model.MessageGenerator;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import static raf.graffito.dsw.core.Type.OBAVESTENJE;
@Getter
public class MainFrame extends JFrame {

    // Buduća polja za sve komponente view-a na glavnom prozoru
    private ActionManager actionManager;
    private GraffTree graffTree;

    public MainFrame() {
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
        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor
        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
        graffTree = new GraffTreeImplementation();
        JTree workspace = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        JPanel desktop = new JPanel();
        JScrollPane scroll=new JScrollPane(workspace);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
    }
}
