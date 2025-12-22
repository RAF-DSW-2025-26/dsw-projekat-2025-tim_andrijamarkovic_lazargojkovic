package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.model.ImageResource;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class ImageThumbnailView extends JPanel
{
    private ImageResource resource;

    public ImageThumbnailView(ImageResource resource)
    {
        this.resource = resource;
        setPreferredSize(new Dimension(120,100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resource.getThumbnail(),10,10,null);
    }
}
