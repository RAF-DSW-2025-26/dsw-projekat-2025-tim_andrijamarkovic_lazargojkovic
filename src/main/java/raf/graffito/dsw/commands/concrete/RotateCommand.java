package raf.graffito.dsw.commands.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.core.GraffSlideElement;

public class RotateCommand implements Command
{
    private GraffSlideElement element;
    private double oldAngle;
    private double newAngle;

    public RotateCommand(GraffSlideElement element, double oldAngle, double newAngle)
    {
        this.element = element;
        this.oldAngle = oldAngle;
        this.newAngle = newAngle;
    }

    @Override
    public void execute()
    {
        element.setRotationAngle(newAngle);
    }

    @Override
    public void undo()
    {
        element.setRotationAngle(oldAngle);
    }
}
