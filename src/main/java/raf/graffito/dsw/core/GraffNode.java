package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class GraffNode {
    protected String name;

    public GraffNode(String name) {
        this.name = name;
    }

    public void dodaj(GraffNode node) {
        throw new UnsupportedOperationException("Nemoguce je dodati na cvor");
    }

    public void remove(GraffNode node) {
        throw new UnsupportedOperationException("Nemoguce izbaciti iz cvora");
    }

    public void ispisi(String naziv) {
    }

    public GraffNode findByName(String name) {
        if(this.name.equals(name)){
            return this;
        }
        return null;
    }
}
