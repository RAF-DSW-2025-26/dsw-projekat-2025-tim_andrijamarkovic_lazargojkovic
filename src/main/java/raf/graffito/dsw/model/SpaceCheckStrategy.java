package raf.graffito.dsw.model;

import raf.graffito.dsw.gui.swing.SlideView;

public interface SpaceCheckStrategy
{
    boolean hasEnoughSpace(SlideView slideView);
}
