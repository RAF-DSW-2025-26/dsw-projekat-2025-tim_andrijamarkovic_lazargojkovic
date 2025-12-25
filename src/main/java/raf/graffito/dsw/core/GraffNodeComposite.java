package raf.graffito.dsw.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
@Getter
//Deo Composite sablona, cvorovi ovog tipa mogu da imaju decu
public abstract class GraffNodeComposite extends GraffNode{

    protected List<GraffNode> children = new ArrayList<>(); //Lista dece nasledjenog cvora

    public GraffNodeComposite() {
        super();
    }

    public GraffNodeComposite(String name,GraffNode parent) {
        super(name,parent);
    }


    public List<GraffNode> getChildren() {
        return children;
    }

    @Override
    public void dodaj(GraffNode node) {
        if (node == null) return;
        children.add(node);
        node.setParent(this);
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
