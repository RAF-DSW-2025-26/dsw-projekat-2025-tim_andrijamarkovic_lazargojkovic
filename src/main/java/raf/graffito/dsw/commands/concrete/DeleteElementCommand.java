package raf.graffito.dsw.commands.concrete;

import raf.graffito.dsw.commands.Command;
import raf.graffito.dsw.core.GraffSlideElement;
import raf.graffito.dsw.model.Slide;

public class DeleteElementCommand implements Command
{
    private Slide slide;
    private GraffSlideElement element;

    public DeleteElementCommand(Slide slide, GraffSlideElement element)
    {
        this.slide = slide;
        this.element = element;
    }


    @Override
    public void execute() {
        slide.removeElement(element);
    }

    @Override
    public void undo() {
        slide.addElement(element);
    }
}
