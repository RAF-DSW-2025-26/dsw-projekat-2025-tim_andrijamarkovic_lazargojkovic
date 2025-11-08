package raf.graffito.dsw.core;

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
