package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ISubscriberView;
import raf.graffito.dsw.model.*;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class SlideView extends JPanel implements ISubscriberView {

    private Slide slide;

    public SlideView() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(GraffNode el : slide.getChildren())
        {
            Graphics2D g2d = (Graphics2D) g;
            if(el instanceof GraffSlideElement)
            {
                if(el instanceof SlidePicture)
                {
                    g2d.drawImage(((SlidePicture)el).getImage(), ((SlidePicture) el).getLocation().x, ((SlidePicture) el).getLocation().y, this);
                }
                else if (el instanceof SlideText)
                {
                    g2d.drawString(((SlideText)el).getText(), ((SlideText) el).getLocation().x, ((SlideText) el).getLocation().y);
                }
                // ovde i treba da ide logo kad ga provalimo kako radi
            }
            g2d.dispose();
        }
    }

    @Override
    public void update(String message, Project project) {

    }
}
