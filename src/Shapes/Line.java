/*
 * A class that represents a line.
 */
package Shapes;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author sinan
 */
public class Line extends Shape {

    public Line() {
        super();
    }

    public Line(Point startPoint) {
        super(startPoint);
    }

    @Override
    public void draw(Graphics g) {
        int x = startPoint.x;
        int y = startPoint.y;
        int lengthX = controlPoint.x;
        int lengthY = controlPoint.y;
        g.setColor(super.getColour());
        g.drawLine(x, y, lengthX, lengthY);
    }

}
