/*
 * A class that represents an rectangle. 
 */
package Shapes;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author sinan
 */
public class Rectangle extends Shape {

    public Rectangle() {
        super();
    }

    public Rectangle(Point startPoint) {
        super(startPoint);
    }

    @Override
    public void draw(Graphics g) {
        int x = Math.min(startPoint.x, controlPoint.x);
        int y = Math.min(startPoint.y, controlPoint.y);
        int width = Math.abs(controlPoint.x - startPoint.x);
        int height = Math.abs(controlPoint.y - startPoint.y);
        g.setColor(super.getColour());
        if (filled) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }
    }
}
