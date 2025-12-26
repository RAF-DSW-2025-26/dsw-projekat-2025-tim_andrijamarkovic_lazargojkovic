package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class ImageResource
{
    private String name;
    private BufferedImage image;
    private Image thumbnail;
    private String path;

    public ImageResource(String name, BufferedImage image,String path)
    {
        this.name = name;
        this.image = image;
        this.thumbnail = image.getScaledInstance(100, 80, Image.SCALE_SMOOTH);
        this.path = path;
    }
}
