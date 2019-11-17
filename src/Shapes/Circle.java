/*
 * A class that represents a circle.
 */
package Shapes;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author sinan
 */
public class Circle extends Oval {

    public Circle() {
        super();
    }

    public Circle(Point startPoint) {
        super(startPoint);
    }

    @Override
    public void draw(Graphics g) {
        int width = Math.abs(controlPoint.x - startPoint.x);
        int height = Math.abs(controlPoint.y - startPoint.y);
        int r = Math.min(width, height);
        int x = startPoint.x - (r / 2);
        int y = startPoint.y - (r / 2);
        
        g.setColor(super.getColour());
        if (filled) {
            g.fillOval(x, y, r, r);
        } else {
            g.drawOval(x, y, r, r);
        }
    }

}
