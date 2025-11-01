package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffNodeComposite;
@Getter
@Setter
public class Presentation extends GraffNodeComposite {

    @Getter
    private static int presentationNumber = 1;
    public Presentation(String name,GraffNode parent) {
        super(name, parent);
        presentationNumber++;
    }
}
