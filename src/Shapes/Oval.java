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
public class Oval extends Shape {

    public Oval() {
        super();
    }

    public Oval(Point startPoint) {
        super(startPoint);
    }

    @Override
    public void draw(Graphics g) {
        int width = Math.abs(controlPoint.x - startPoint.x);
        int height = Math.abs(controlPoint.y - startPoint.y);
        int x = startPoint.x - (width / 2);
        int y = startPoint.y - (height / 2);

        g.setColor(super.getColour());
        if (filled) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }

}
