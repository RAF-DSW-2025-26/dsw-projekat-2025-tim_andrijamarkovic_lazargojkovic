package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Getter
@Setter
public class SlideLabel extends GraffSlideElement {

    private String text;

    public SlideLabel(){
        super();
    }

    public SlideLabel(String name, GraffNode parent, int x, int y, int width, int height, double rotationAngle, String text) {
        super(name, parent, x, y, width, height, rotationAngle);
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g) {
        //čuva staru transformaciju i stavlja je u temp novu na kojoj radi izmene (rotira)
        AffineTransform old = g.getTransform();
        AffineTransform at = new AffineTransform(old);
        double cx = getX() + getWidth()/2.0;
        double cy = getY() + getHeight()/2.0;
        at.rotate(getRotationAngle(), cx, cy);
        g.setTransform(at);
        //metrika fonta odredjuje visinu fonta i dužinu teksta da bi generisao širinu i dužinu string elementu (zarad selekcije, pomeranja, resize-a itd.)
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        setWidth(textWidth);
        setHeight(textHeight);
        g.drawString(text,getX(),getY()+fm.getAscent());
        g.setTransform(old);
    }

    @Override
    public GraffNode findByName(String name) {
        return super.findByName(name);
    }


    @Override
    public GraffSlideElement graffClone() {
        return new SlideLabel(getName(), getParent(), getX(), getY(), getWidth(), getHeight(), getRotationAngle(), text);
    }
}
