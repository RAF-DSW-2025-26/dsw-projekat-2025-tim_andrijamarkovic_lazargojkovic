package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.ISubscriber;
import raf.graffito.dsw.model.Project;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ProjectView extends JPanel implements ISubscriber
{
    private JScrollPane scroll;
    private JLabel presentationName;
    private JLabel projectName;
    private JLabel author;
    private Project project;

    public ProjectView() {
        setLayout(new GridLayout(3,1,10,10));
        presentationName = new JLabel();
        projectName = new JLabel();
        author = new JLabel();
        add(presentationName);
        add(projectName);
        add(author);
    }

    public void subscribe(Project project)
    {
        project.addSubscriber(this);
        project.addSubscriber(MainFrame.getInstance().getTabbedPane());
    }

    @Override
    public void update(String message)
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
                MainFrame.getInstance().getTabbedPane().addTab(project.getChildren().getLast().getName(), panel);
                break;
            case "presentationRemoved":
                MainFrame.getInstance().getTabbedPane().removeTabAt(MainFrame.getInstance().getTabbedPane().getSelectedIndex());
        }
    }
}
