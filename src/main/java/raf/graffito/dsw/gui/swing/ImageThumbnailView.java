package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.model.ImageResource;
import raf.graffito.dsw.state.concrete.AddImageState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class ImageThumbnailView extends JPanel
{
    private ImageResource resource;
    // prikaz thumbnail-ova slika
    public ImageThumbnailView(ImageResource resource)
    {
        this.resource = resource;
        setPreferredSize(new Dimension(120,100));
        // na kilk mišem na učitanu sliku prebacuje se u State za dodavanje slike
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
                activateAddImageState();
            }
        });
    }

    private void activateAddImageState()
    {
        AddImageState addImageState = MainFrame.getInstance().getStateManager().getAddImageState();
        addImageState.setSelectedImage(resource);
        MainFrame.getInstance().getStateManager().setAddImageState();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(resource.getThumbnail(),10,10,null);
    }
}
