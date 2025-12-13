package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public abstract class GraffSlideElement extends GraffLeaf{

    private int x;
    private int y;
    private int width;
    private int height;
    private double rotationAngle;
    private final double rotateRatio = 90.0;

    public GraffSlideElement(String name, GraffNode parent, int x,int y, int width, int height, double rotationAngle) {
        super(name, parent);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotationAngle = rotationAngle;
    }

    public abstract void draw(Graphics2D g);

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }
}
