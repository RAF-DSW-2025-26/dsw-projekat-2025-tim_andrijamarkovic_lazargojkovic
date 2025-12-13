package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.Cloneable;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public class SlidePicture extends GraffSlideElement implements Cloneable {

    private Image image;

    public SlidePicture(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle, Image image) {
        super(name, parent, x, y, width, height, rotationAngle);
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform old = g.getTransform();
        AffineTransform at = new AffineTransform(old);
        double cx = getX() + getWidth()/2.0;
        double cy = getY() + getHeight()/2.0;
        at.rotate(getRotationAngle(), cx, cy);
        g.setTransform(at);
        g.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
        g.setTransform(old);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlidePicture(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle(), getImage());
    }
}
