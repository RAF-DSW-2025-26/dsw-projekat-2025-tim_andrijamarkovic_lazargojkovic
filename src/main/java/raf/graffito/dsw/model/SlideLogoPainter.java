package raf.graffito.dsw.model;

import java.awt.*;
import java.awt.geom.Path2D;

public class SlideLogoPainter
{
    public static void paint(SlideLogo slideLogo, Graphics2D g)
    {
        int x = slideLogo.getX();
        int y = slideLogo.getY();
        int w = slideLogo.getWidth();
        int h = slideLogo.getHeight();

        //neke random linije
        Path2D path2D = new Path2D.Double();
        path2D.moveTo(x, y + h/2);
        path2D.lineTo(x + w, y + h/2);
        path2D.lineTo(x + w, y + h);
        path2D.lineTo(x, y + h);
        path2D.lineTo(x + w, y);
        path2D.lineTo(x, y);
        g.fill(path2D);
    }
}
