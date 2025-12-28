package raf.graffito.dsw.model;

import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.gui.swing.SlideView;

public class SpaceChecker implements SpaceCheckStrategy {

    @Override
    public boolean hasEnoughSpace(SlideView slideView)
    {
        int w = slideView.getWidth();
        int h = slideView.getHeight();
        boolean[][] occupied = new boolean[w][h]; // matrica boolean vrednosti u kojoj boolean true predstavlja zauzet piksel
        for(GraffNode el : slideView.getSlideForView(slideView).getChildren())
        {
            if(el instanceof GraffSlideElement)
            {
                for(int x = ((GraffSlideElement) el).getX(); x<((GraffSlideElement) el).getX() + ((GraffSlideElement) el).getWidth(); x++)
                {
                    for(int y = ((GraffSlideElement) el).getY(); y<((GraffSlideElement) el).getY() + ((GraffSlideElement) el).getHeight(); y++)
                    {
                        if(x>=0 && x<w && y>=0 && y<h)
                        {
                            occupied[x][y] = true;
                        }
                    }
                }
            }
        }
        // računanje broja slobodnih piksela
        int free = 0;
        for(int x = 0; x<w; x++)
        {
            for(int y = 0; y<h; y++)
            {
                if(!occupied[x][y])
                {
                    free++;
                }
            }
        }
        return free >= 0.2*w*h; // ukoliko je više od 20% ekrana, može se dodati novi element
    }
}
