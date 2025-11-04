package raf.graffito.dsw.decorator;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;

import java.awt.*;
@Getter
@Setter
public class ColorDecorator extends NodeDecorator {
    private Color color;

    public ColorDecorator(String name, GraffNode parent, Color color) {
        super(name, parent);
        this.color = color;
    }
}
