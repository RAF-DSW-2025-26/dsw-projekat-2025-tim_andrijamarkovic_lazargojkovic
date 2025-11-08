package raf.graffito.dsw.core;

import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;

import java.util.ArrayList;
import java.util.List;

public class GraffRepositoryImpl implements GraffRepository
{

    private Workspace workspace;
    private List<RepositoryListener> listeners = new ArrayList<>();

    public GraffRepositoryImpl()
    {
        workspace = new Workspace("My Workspace");
    }

    public void addListener(RepositoryListener listener) {
        listeners.add(listener);
    }

    public void removeListener(RepositoryListener listener) {
        listeners.remove(listener);
    }

    @Override
    public Workspace getWorkspace()
    {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child)
    {
    }

    @Override
    public Project projectFactory(GraffNode parent)
    {
        int nextProject = Project.getProjectNumber();
        return new Project("Project" + nextProject, "", "", parent);
    }

    @Override
    public Presentation presentationFactory(GraffNode parent)
    {
        int nextPresentation = Presentation.getPresentationNumber();
        return new Presentation("Presentation" + nextPresentation, parent);
    }

    @Override
    public Slide slideFactory(GraffNode parent)
    {
        int nextSlide = Slide.getSlideNumber();
        return new Slide("Slide" + nextSlide, parent);
    }
}
