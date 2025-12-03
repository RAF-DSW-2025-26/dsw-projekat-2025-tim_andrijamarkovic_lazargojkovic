package raf.graffito.dsw.model;

import lombok.Getter;
import raf.graffito.dsw.core.GraffLeaf;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;

public class Slide extends GraffNodeComposite {

    @Getter
    private static int slideNumber = 1; //Integer koji prati broj kreiranih slajdova
    public Slide(String name, GraffNode parent) {
        super(name,parent);
        slideNumber++;
    }
}
