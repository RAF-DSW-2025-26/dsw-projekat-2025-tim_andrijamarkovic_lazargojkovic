package raf.graffito.dsw.repository;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;
//Klasa koja implementira metode iz GraffRepository koje koriste Factory Sablon za kreiranje novih cvorova
public class GraffRepositoryImpl implements GraffRepository
{

    private Workspace workspace;

    public GraffRepositoryImpl()
    {
        workspace = new Workspace("My Workspace");
    }

    @Override
    public Workspace getWorkspace()
    {
        return workspace;
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
