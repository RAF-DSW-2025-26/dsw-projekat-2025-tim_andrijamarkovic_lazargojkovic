package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter

public class SlidePicture extends GraffSlideElement {

    private Image image;

    public SlidePicture(Image image, String name, GraffNode parent, Dimension dimension, Point location, double rotationAngle) {
        super(name, parent, dimension, location, rotationAngle);
        this.image=image;
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlidePicture(image, getName(), getParent(), getDimension(), getLocation(), getRotationAngle());
    }
}
