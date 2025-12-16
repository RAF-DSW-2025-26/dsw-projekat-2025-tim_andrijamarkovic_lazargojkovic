package raf.graffito.dsw.commands.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.core.GraffSlideElement;

import java.awt.*;

public class ResizeCommand implements Command
{
    private GraffSlideElement element;
    private Rectangle oldBounds;
    private Rectangle newBounds;

    public ResizeCommand(GraffSlideElement element, Rectangle oldBounds, Rectangle newBounds) {
        this.element = element;
        this.oldBounds = oldBounds;
        this.newBounds = newBounds;
    }

    private void apply(Rectangle bounds)
    {
        element.setX(bounds.x);
        element.setY(bounds.y);
        element.setWidth(bounds.width);
        element.setHeight(bounds.height);
    }

    @Override
    public void execute()
    {
        apply(newBounds);
    }

    @Override
    public void undo()
    {
        apply(oldBounds);
    }
}
