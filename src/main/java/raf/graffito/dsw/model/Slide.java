package raf.graffito.dsw.model;

import raf.graffito.dsw.core.GraffLeaf;
import raf.graffito.dsw.core.GraffNode;

public class Slide extends GraffLeaf {
    private String content;

    public Slide(String name, String content) {
        super(name);
        this.content = content;
    }

    public String getContent() { return content; }
}
