package raf.graffito.dsw.repository;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
import raf.graffito.dsw.model.Workspace;

public class GraffRepositoryImpl implements GraffRepository
{

    private Workspace workspace;

    public GraffRepositoryImpl()
    {
        workspace = new Workspace("Workspace");
    }

    @Override
    public Workspace getWorkspace()
    {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child)
    {
        //TODO
    }
}
