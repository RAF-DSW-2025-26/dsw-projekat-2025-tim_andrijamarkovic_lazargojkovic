package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public class SlideLogo extends GraffSlideElement implements Cloneable {

    public SlideLogo(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle) {
        super(name, parent, x, y, width, height, rotationAngle);
    }

    @Override
    public void draw(Graphics2D g) {
        //čuva staru transformaciju i stavlja je u temp novu na kojoj radi izmene (rotira)
        AffineTransform old = g.getTransform();
        AffineTransform at = new AffineTransform(old);
        //pamćenje centra elementa
        double cx = getX() + getWidth()/2.0;
        double cy = getY() + getHeight()/2.0;
        at.rotate(getRotationAngle(), cx, cy);
        g.setTransform(at);
        SlideLogoPainter.paint(this, g);
        g.setTransform(old);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlideLogo(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle());
    }
}
