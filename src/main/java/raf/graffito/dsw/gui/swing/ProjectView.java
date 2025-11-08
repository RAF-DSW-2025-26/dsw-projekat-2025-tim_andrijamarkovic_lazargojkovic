package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.core.ISubscriber;
import raf.graffito.dsw.core.ISubscriberView;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriberView
{
    private JScrollPane scroll;
    private JLabel presentationName;
    private JLabel projectName;
    private JLabel author;

    public ProjectView()
    {
        setLayout(new BorderLayout(10,0));
        presentationName = new JLabel("Ime Prezentacije: ");
        projectName = new JLabel("Ime Projekta: ");
        author = new JLabel("Autor: ");
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new BorderLayout());
        panelTop.add(presentationName, BorderLayout.NORTH);
        panelTop.add(projectName, BorderLayout.CENTER);
        panelTop.add(author, BorderLayout.SOUTH);
        add(panelTop, BorderLayout.NORTH);
    }

    public void subscribe(Project project)
    {
        project.addSubscriber(this);
        project.addSubscriber(MainFrame.getInstance().getTabbedPane());
    }

    @Override
    public void update(String message, Project project)
    {
        switch (message)
        {
            case "nameChanged":
                projectName.setText("Ime Projekta: " + project.getName());
                break;
            case "authorChanged":
                author.setText("Autor: " + project.getAuthor());
                break;
            case "nameChangedPresentation":
                presentationName.setText("Ime Prezentacije: " + MainFrame.getInstance().getTabbedPane().getTitleAt(MainFrame.getInstance().getTabbedPane().getSelectedIndex()));
                break;
            case "presentationAdded":
                JPanel panel = new JPanel(new BorderLayout());
                MainFrame.getInstance().getTabbedPane().addTab(MainFrame.getInstance().getGraffTree().getSelectedNode().getChildren().getLast().toString(), panel);
                Color color = MainFrame.getInstance().getTabbedPane().getUsedColors().get(MainFrame.getInstance().getGraffTree().getSelectedNode());
                for(int i = 0; i < MainFrame.getInstance().getTabbedPane().getTabCount(); i++)
                {
                    MainFrame.getInstance().getTabbedPane().setBackgroundAt(i,color);
                }
                break;
            case "presentationRemoved":
                String currentName = MainFrame.getInstance().getGraffTree().getSelectedNode().toString();
                for(int i = 0; i < MainFrame.getInstance().getTabbedPane().getTabCount(); i++)
                {
                    String tabTitle = MainFrame.getInstance().getTabbedPane().getTitleAt(i);
                    if(tabTitle.equals(currentName))
                    {
                        MainFrame.getInstance().getTabbedPane().remove(i);
                        break;
                    }
                }
                break;
            case "projectRemoved":
                MainFrame.getInstance().getTabbedPane().removeAll();
                presentationName.setText("Ime Prezentacije: ");
                projectName.setText("Ime Projekta: ");
                author.setText("Autor: ");
                break;
        }
    }
}
