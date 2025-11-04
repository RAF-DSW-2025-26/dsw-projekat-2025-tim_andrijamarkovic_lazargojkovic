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
    void addChild(GraffNodeComposite parent, GraffNode child);
    Project projectFactory(GraffNode parent);
    Presentation presentationFactory(GraffNode parent);
    Slide slideFactory(GraffNode parent);
}
