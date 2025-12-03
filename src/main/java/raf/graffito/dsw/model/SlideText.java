package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter
public class SlideText extends GraffSlideElement {
    public SlideText(String name, GraffNode parent, Dimension dimension, Point location, double rotationAngle) {
        super(name, parent, dimension, location, rotationAngle);
    }

    @Override
    public void dodaj(GraffNode node) {
        super.dodaj(node);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }
}
