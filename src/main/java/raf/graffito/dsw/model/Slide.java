package raf.graffito.dsw.model;

import lombok.Getter;
import raf.graffito.dsw.core.GraffLeaf;
import raf.graffito.dsw.core.GraffNode;

public class Slide extends GraffLeaf {

    @Getter
    private static int slideNumber = 1;
    public Slide(String name, GraffNode parent) {
        super(name,parent);
        slideNumber++;
    }
}
