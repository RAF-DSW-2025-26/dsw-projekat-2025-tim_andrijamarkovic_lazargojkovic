package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public abstract class GraffSlideElement extends GraffLeaf{

    private int x;
    private int y;
    private int width;
    private int height;
    private double rotationAngle;
    private double rotateRatio;

    public GraffSlideElement(String name, GraffNode parent, int x,int y, int width, int height, double rotationAngle, double rotateRatio) {
        super(name, parent);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotationAngle = rotationAngle;
        this.rotateRatio = rotateRatio;
    }

    public abstract void draw(Graphics2D g);

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }
}
