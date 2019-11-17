/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package Main;

import Shapes.Rectangle;
import Shapes.Line;
import Shapes.Oval;
import Shapes.Shape;
import Shapes.Square;
import Shapes.Circle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author mvn2409
 */
public class ShapeSketcher extends JPanel implements ActionListener {

    private Point endPoint, startPoint;
    private Color c;

    private JRadioButton lineRadio, ovalRadio, circleRadio, rectangleRadio, squareRadio;
    private JButton colorButton, clearButton, undoButton, exitButton, openButton, saveButton;
    private JCheckBox setFilled;

    private Shape shape;

    private ArrayList<Shape> tempShapes, shapes;

    private boolean dragging;
    private JFileChooser chooser;
    private final String ObjButtons[] = {"Yes", "No"};

    public final int MIN_WIDTH = 310;
    public final int MIN_HEIGHT = 310;

    public ShapeSketcher() {
        //invoke super class Jpanel constructor
        super(new BorderLayout());

        lineRadio = new JRadioButton("Line");
        lineRadio.setSelected(true);
        ovalRadio = new JRadioButton("Oval");
        circleRadio = new JRadioButton("Circle");
        rectangleRadio = new JRadioButton("Rectangle");
        squareRadio = new JRadioButton("Square");
        colorButton = new JButton("Color");
        colorButton.addActionListener(this);
        setFilled = new JCheckBox("Set Filled");

        ButtonGroup shapeRadios = new ButtonGroup();
        shapeRadios.add(lineRadio);
        shapeRadios.add(ovalRadio);
        shapeRadios.add(circleRadio);
        shapeRadios.add(rectangleRadio);
        shapeRadios.add(squareRadio);

        JPanel shapesPanel = new JPanel();
        shapesPanel.add(lineRadio);
        shapesPanel.add(ovalRadio);
        shapesPanel.add(circleRadio);
        shapesPanel.add(rectangleRadio);
        shapesPanel.add(squareRadio);
        shapesPanel.add(setFilled);
        shapesPanel.add(colorButton);
        add(shapesPanel, BorderLayout.NORTH);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        undoButton = new JButton("Undo");
        undoButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        openButton = new JButton("Open");
        openButton.addActionListener(this);

        JPanel optionsPanel = new JPanel();
        optionsPanel.add(clearButton);
        optionsPanel.add(undoButton);
        optionsPanel.add(openButton);
        optionsPanel.add(saveButton);
        optionsPanel.add(exitButton);
        add(optionsPanel, BorderLayout.SOUTH);

        DrawingPanel drawPanel = new DrawingPanel();
        add(drawPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == saveButton) {
            chooser = new JFileChooser(new File("."));
            int status = chooser.showSaveDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    ObjectOutputStream shapesOut = new ObjectOutputStream(new FileOutputStream(chooser.getSelectedFile()));
                    shapesOut.writeInt(shapes.size());
                    for (Shape currentShape : shapes) {
                        shapesOut.writeObject(currentShape);
                    }
                    shapesOut.flush();
                    shapesOut.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving the file", "Save Failed - Shape Sketcher", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (source == openButton) {
            chooser = new JFileChooser(new File("."));
            int status = chooser.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                int PromptResult = JOptionPane.showOptionDialog(null, "ARE YOU SURE YOU WANT TO DO THIS? \nThis will clear all the shapes currently drawn", "Shape Sketcher",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                if (PromptResult == 0) {
                    try {
                        clear();
                        FileInputStream file = new FileInputStream(chooser.getSelectedFile());
                        ObjectInputStream shapesIn = new ObjectInputStream(file);
                        int size = shapesIn.readInt();
                        for (int i = 0; i < size; i++) {
                            shapes.add((Shape) shapesIn.readObject());
                        }
                        shapesIn.close();
                        repaint();
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Error openning/reading the file", "Open Failed - Shape Sketcher", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (source == undoButton) {
            if (shapes.size() > 0) {
                shapes.remove(shapes.size() - 1);
                repaint();
            }
        } else if (source == clearButton) {
            int PromptResult = JOptionPane.showOptionDialog(null, "ARE YOU SURE YOU WANT TO CLEAR CURRENTLY DRAWN SHAPES?", "Shape Sketcher",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
            if (PromptResult == 0) {
                clear();
            }
        } else if (source == colorButton) {
            c = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
            colorButton.setBackground(c);
        } else if (source == exitButton) {
            int PromptResult = JOptionPane.showOptionDialog(null, "ARE YOU SURE YOU WANT TO EXIT?", "Shape Sketcher",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
            if (PromptResult == 0) {
                System.exit(0);
            }
        }
    }

    private void clear() {
        shapes.clear();
        repaint();
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("Sketch Pad");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ShapeSketcher());
        frame.pack();
        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2, (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
    }

    private class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {

        public DrawingPanel() {
            setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
            tempShapes = new ArrayList();
            shapes = new ArrayList();
            addMouseListener(this);
            addMouseMotionListener(this);
            setBackground(Color.WHITE);
            c = Color.BLACK;
            colorButton.setBackground(c);
            colorButton.setForeground(Color.WHITE);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            dragging = false;
            startPoint = new Point(e.getX(), e.getY());
            endPoint = new Point(e.getX(), e.getY());
            if (lineRadio.isSelected()) {
                shape = new Line(startPoint);
            } else if (ovalRadio.isSelected()) {
                shape = new Oval(startPoint);
            } else if (circleRadio.isSelected()) {
                shape = new Circle(startPoint);
            } else if (rectangleRadio.isSelected()) {
                shape = new Rectangle(startPoint);
            } else if (squareRadio.isSelected()) {
                shape = new Square(startPoint);
            }
            shape.setFilled(setFilled.isSelected());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (dragging) {
                shape.setControlPoint(endPoint);
                shapes.add(tempShapes.get(tempShapes.size() - 1));
                tempShapes.clear();
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            dragging = true;
            shape.setColour(c);
            endPoint.setLocation(e.getX(), e.getY());
            shape.setControlPoint(endPoint);
            tempShapes.add(shape);
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Shape shape : shapes) {
                shape.draw(g);
            }
            for (Shape shape : tempShapes) {
                shape.draw(g);
                if (startPoint.x == endPoint.x) {
                    g.drawString("V", endPoint.x, endPoint.y);
                }
                if (startPoint.y == endPoint.y) {
                    g.drawString("H", endPoint.x, endPoint.y);
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }
    }
}
