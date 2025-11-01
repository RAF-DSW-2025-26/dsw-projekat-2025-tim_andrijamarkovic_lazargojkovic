package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNodeComposite;
@Getter
@Setter
public class Workspace extends GraffNodeComposite {

    @Getter
    private static int workspaceNumber = 0;

    public Workspace(String name) {
        super(name,null);
    }

}
