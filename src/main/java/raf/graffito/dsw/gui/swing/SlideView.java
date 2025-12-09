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
        setLayout(new BorderLayout());
        slideLabel = new JLabel("Slide", SwingConstants.CENTER);
        slideLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        add(slideLabel, BorderLayout.CENTER);
        setBackground(Color.WHITE);
    }

    public void setSlide(Slide slide) {
        this.slide = slide;
        if(slide != null) {
            slideLabel.setText("Ovo je slide " + slide.getName());
        }
        else
        {
            slideLabel.setText("Slide");
        }
        revalidate();
        repaint();
    }
    @Override
    public void update(String message, Project project) {

    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // ili koliko želiš
    }
}
