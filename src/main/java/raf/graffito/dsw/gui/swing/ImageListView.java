package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.core.ISubscriber;
import raf.graffito.dsw.core.ImageRepository;
import raf.graffito.dsw.model.ImageResource;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ImageListView extends JPanel implements ISubscriber
{
    private JPanel contentPane;

    public ImageListView()
    {
        MainFrame.getInstance().getImageRepository().addSubscriber(this);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 400));
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPane);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void update(String message)
    {
        if(!"imageAdded".equals(message)) return;
        contentPane.removeAll();
        for(ImageResource img : MainFrame.getInstance().getImageRepository().getImages())
        {
            contentPane.add(new ImageThumbnailView(img));
        }
        revalidate();
        repaint();
    }
}
