package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.core.ISubscriberView;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SlideView extends JPanel implements ISubscriberView {

    private Slide slide;
    private JLabel slideLabel;

    public SlideView() {
        setMinimumSize(new Dimension(400, 400));
        setSize(400,400);
        slideLabel = new JLabel();
        slideLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        slideLabel.setHorizontalAlignment(SwingConstants.CENTER);
        slideLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        add(slideLabel);
        setBackground(Color.WHITE);
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
        slideLabel.setText("Ovo je slide " + slide.getName());
        repaint();
    }
/*
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
*/
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Oboji pozadinu
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());

    // Crtaj ime slajda
    g.setColor(Color.BLACK);
    g.setFont(new Font("Arial", Font.BOLD, 24));
    g.drawString(slide.getName(), 20, 40);
}
    @Override
    public void update(String message, Project project) {

    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // ili koliko želiš
    }
}
