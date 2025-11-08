package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Baza Composite sablona
public abstract class GraffNode {
    protected String name;
    protected GraffNode parent;

    public GraffNode(String name, GraffNode parent)
    {
        this.name = name;
        this.parent = parent;
    }

    public void dodaj(GraffNode node) {

    }

    public GraffNode findByName(String name) {
       return null;
    }
}
