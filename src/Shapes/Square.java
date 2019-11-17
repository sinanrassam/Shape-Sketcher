/*
 * A class that represents a square. 
 */
package Shapes;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author sinan
 */
public class Square extends Rectangle {

    public Square() {
        super();
    }

    public Square(Point startPoint) { 
        super(startPoint);
    }

    @Override
    public void draw(Graphics g) {
        int x = Math.min(startPoint.x, controlPoint.x);
        int y = Math.min(startPoint.y, controlPoint.y);
        
        int width = Math.abs(controlPoint.x - startPoint.x);
        int height = Math.abs(controlPoint.y - startPoint.y);
        
        int size = Math.min(width, height);
        
        if (startPoint.x > controlPoint.x) {
            x = startPoint.x - size;
        }
        if (startPoint.y > controlPoint.y) {
            y = startPoint.y - size;
        }
        
        g.setColor(super.getColour());
        
        if (filled) {
            g.fillRect(x, y, size, size);
        } else {
            g.drawRect(x, y, size, size);
        }
    }
}
