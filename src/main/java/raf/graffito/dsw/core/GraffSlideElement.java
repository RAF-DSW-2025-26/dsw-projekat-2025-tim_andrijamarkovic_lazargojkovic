package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class GraffSlideElement extends GraffLeaf{

    private Dimension dimension;
    private Point location;
    private double rotationAngle;

    public GraffSlideElement(String name, GraffNode parent, Dimension dimension, Point location, double rotationAngle) {
        super(name, parent);
        this.dimension = dimension;
        this.location = location;
        this.rotationAngle = rotationAngle;
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }


}
