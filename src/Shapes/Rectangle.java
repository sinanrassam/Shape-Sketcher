/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Shapes;

import Shapes.Shape;
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
