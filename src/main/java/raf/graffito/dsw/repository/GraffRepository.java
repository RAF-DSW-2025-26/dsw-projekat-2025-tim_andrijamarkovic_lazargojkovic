package raf.graffito.dsw.repository;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;

public interface GraffRepository
{
    Workspace getWorkspace();
    void addChild(GraffNodeComposite parent, GraffNode child);
}
