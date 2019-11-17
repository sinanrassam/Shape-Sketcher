/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author sinan
 */
public abstract class Shape implements Serializable, EnclosesRegion {
    
    protected Point startPoint, controlPoint;
    private Color colour;
    protected boolean filled;
    
    public Shape() {
        this(new Point());
    }
    
    public Shape(Point startPoint) {
        this.startPoint = startPoint;
    }
    
    public Color getColour() {
        return colour; 
    }
    
    public void setColour(Color colour) {
        this.colour = colour;
    }
    
    public void setControlPoint(Point controlPoint) {
        this.controlPoint = controlPoint;
    }

    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    
    public abstract void draw(Graphics g);
    
    @Override
    public String toString() {
        return "(" + startPoint.getX() + ", " + startPoint.getY() + ") to (" + controlPoint.getX() + ", " + controlPoint.getY() + ")";
    }
}
