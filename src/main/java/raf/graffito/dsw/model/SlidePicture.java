package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.Cloneable;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter

public class SlidePicture extends GraffSlideElement implements Cloneable {

    private Image image;

    public SlidePicture(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle, double rotateRatio, Image image) {
        super(name, parent, x, y, width, height, rotationAngle,rotateRatio);
        this.image = image;
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }

    @Override
    public GraffSlideElement clone() {
        return new SlidePicture(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle(), getRotationAngle(), getImage());
    }
}
