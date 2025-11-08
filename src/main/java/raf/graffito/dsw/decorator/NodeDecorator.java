package raf.graffito.dsw.decorator;

import lombok.Getter;
import raf.graffito.dsw.core.GraffNode;
@Getter
//Bazican Decorator koji se koristi kao baza Decorator sablona
public abstract class NodeDecorator extends GraffNode {
    protected GraffNode node;


    public NodeDecorator(String name, GraffNode parent) {
        super(name, parent);
    }

}
