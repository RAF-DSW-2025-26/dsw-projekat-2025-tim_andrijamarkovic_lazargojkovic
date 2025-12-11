package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter
public class SlideLogo extends GraffSlideElement implements Cloneable {

    public SlideLogo(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle, double rotateRatio) {
        super(name, parent, x, y, width, height, rotationAngle,rotateRatio);
    }

    @Override
    public void draw(Graphics2D g) {
        SlideLogoPainter.paint(this, g);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlideLogo(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle(), getRotateRatio());
    }
}
