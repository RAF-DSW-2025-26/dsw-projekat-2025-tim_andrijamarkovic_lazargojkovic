package raf.graffito.dsw.core;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
//Deo Composite sablona, cvorovi ovog tipa mogu da imaju decu
public abstract class GraffNodeComposite extends GraffNode{

    protected List<GraffNode> children = new ArrayList<>(); //Lista dece nasledjenog cvora

    public GraffNodeComposite(String name,GraffNode parent) {
        super(name,parent);
    }

    @Override
    public void dodaj(GraffNode node) {
        super.dodaj(node);
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
