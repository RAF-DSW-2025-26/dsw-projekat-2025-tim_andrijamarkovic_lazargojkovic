package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter
public class SlideText extends GraffSlideElement {

    private String text;

    public SlideText(String text, String name, GraffNode parent, Dimension dimension, Point location, double rotationAngle) {
        super(name, parent, dimension, location, rotationAngle);
        this.text = text;
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlideText(text, getName(), getParent(), getDimension(), getLocation(), getRotationAngle());
    }
}
