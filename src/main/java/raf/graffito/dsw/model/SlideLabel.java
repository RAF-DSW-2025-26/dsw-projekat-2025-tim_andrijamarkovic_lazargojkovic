package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.Cloneable;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
@Getter
@Setter
public class SlideLabel extends GraffSlideElement implements Cloneable {

    private String text;

    public SlideLabel(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle, double rotateRatio, String text) {
        super(name, parent, x, y, width, height, rotationAngle,rotateRatio);
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g) {
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        setWidth(textWidth);
        setHeight(textHeight);
        g.drawString(text,getX(),getY()+fm.getAscent());
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }


    @Override
    public GraffSlideElement clone() {
        return new SlideLabel(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle(), getRotateRatio(), text);
    }
}
