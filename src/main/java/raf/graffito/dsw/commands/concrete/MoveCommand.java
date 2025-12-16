package raf.graffito.dsw.commands.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.core.GraffSlideElement;

public class MoveCommand implements Command
{
    private GraffSlideElement element;
    private int oldX, oldY;
    private int newX, newY;

    public MoveCommand(GraffSlideElement element, int oldX, int oldY, int newX, int newY)
    {
        this.element = element;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }


    @Override
    public void execute()
    {
        element.setX(newX);
        element.setY(newY);
    }

    @Override
    public void undo()
    {
        element.setX(oldX);
        element.setY(oldY);
    }
}
