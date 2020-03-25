package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }
    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //creates a border around the canvas
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) { // handling the mouse pressed event
                drawShape(e.getX(), e.getY()); //drawing at the cursor's coords
                repaint();
            }
        });
    }
    private void drawShape(int x, int y) {

        Random ran = new Random();
        //setting the radius
        int radius = ran.nextInt(27) + 4; // 4 ... 30 //generate a random number
        //setting the number of sides
        int sides = (int) ConfigPanel.sidesField.getValue(); //getting the value from the ConfigPanel
        //setting the color
        switch (ConfigPanel.colorCombo.getSelectedItem().toString()){//getting the color from the ConfigPanel
            case "Random":
                int r = ran.nextInt(128) + 128; // 128 ... 255
                int g = ran.nextInt(128) + 128; // 128 ... 255
                int b = ran.nextInt(128) + 128; // 128 ... 255
                Color col = new Color(r, g, b); //lighter colors are between 128 and 255
                graphics.setColor(col);
                break;
            case "Black":
                graphics.setColor(new Color(0,0,0));
                break;
        }

        switch (ConfigPanel.shapeCombo.getSelectedItem().toString()){
            case "Polygon":
                graphics.fill(new RegularPolygon(x, y, radius, sides));//drawing a polygon with the given params
                break;
            case "Circle":
                graphics.fill(new Circle(x, y, radius));//drawing a circle with the given params
                break;
        }
    }

    @Override
    public void update(Graphics g) { } //this function updates the canvas. without it, nothing can be drawn

    @Override
    protected void paintComponent(Graphics g) { // this function simply draws the image
        g.drawImage(image, 0, 0, this);
    }

}
