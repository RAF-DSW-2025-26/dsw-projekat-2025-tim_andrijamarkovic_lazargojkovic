package raf.graffito.dsw.repository;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.model.Presentation;
import raf.graffito.dsw.model.Project;
import raf.graffito.dsw.model.Slide;
import raf.graffito.dsw.model.Workspace;
public interface GraffRepository
{
    Workspace getWorkspace();
    Project projectFactory(GraffNode parent); //Factory koji kreira projekte
    Presentation presentationFactory(GraffNode parent); //Factory koji kreira prezentacije
    Slide slideFactory(GraffNode parent); //Factory koji kreira slajdove
}
