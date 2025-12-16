package raf.graffito.dsw.model;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.GraffNode;
import raf.graffito.dsw.core.GraffSlideElement;

@Getter
@Setter
public class SlideClipboard
{
    private static GraffSlideElement copiedElement;

    public static void copy(GraffSlideElement element)
    {
        if(element != null)
        {
            copiedElement = element.graffClone();
        }
    }

    public static GraffSlideElement paste(GraffNode newParent)
    {
        if(copiedElement == null) return null;

        GraffSlideElement clone = copiedElement.graffClone();
        clone.setParent(newParent);
        clone.setX(clone.getX() + 20);
        clone.setY(clone.getY() + 20);
        return clone;
    }
}
