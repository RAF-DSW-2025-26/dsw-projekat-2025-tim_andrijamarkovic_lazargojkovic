package raf.graffito.dsw.model;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class ImageResource
{
    private String name;
    private BufferedImage image;
    private Image thumbnail;

    public ImageResource(String name, BufferedImage image)
    {
        this.name = name;
        this.image = image;
        this.thumbnail = image.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
    }
}
