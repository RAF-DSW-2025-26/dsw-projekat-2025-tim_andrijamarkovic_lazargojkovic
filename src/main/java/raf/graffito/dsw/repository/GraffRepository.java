package raf.graffito.dsw.repository;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.model.Workspace;

public class GraffRepository {
    private Workspace root;

    public GraffRepository(String workspaceName) {
        root = new Workspace(workspaceName);
    }

    public Workspace getWorkspace() {
        return root;
    }

    public void displayStructure() {
        root.ispisi("");
    }

    public GraffNode find(String name) {
        return root.findByName(name);
    }
}
