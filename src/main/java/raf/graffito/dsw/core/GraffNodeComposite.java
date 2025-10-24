package raf.graffito.dsw.core;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode{

    protected List<GraffNode> children = new ArrayList<>();

    public GraffNodeComposite(String name) {
        super(name);
    }

    @Override
    public void dodaj(GraffNode node) {
        super.dodaj(node);
    }

    @Override
    public void remove(GraffNode node) {
        super.remove(node);
    }

    @Override
    public void ispisi(String naziv) {
        System.out.println(naziv + getName());
        for(GraffNode child: children){
            child.ispisi(naziv + " ");
        }
    }

    @Override
    public GraffNode findByName(String name) {
        if (this.name.equals(name)) return this;
        for (GraffNode child : children) {
            GraffNode found = child.findByName(name);
            if (found != null) return found;
        }
        return null;
    }

}
