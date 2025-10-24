package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
@Getter
@Setter
public class Presentation extends GraffNodeComposite {
    public Presentation(String name) {
        super(name);
    }

    @Override
    public void dodaj(GraffNode node) {
        if (node instanceof Slide) {
            super.dodaj(node);
        } else {
            throw new IllegalArgumentException("Prezentacija moze da sadrzi samo slajdove");
        }
    }
}
