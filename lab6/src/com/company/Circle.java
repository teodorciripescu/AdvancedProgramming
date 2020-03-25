package com.company;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Ellipse2D.Double {
    public Circle(int x0, int y0, int radius){
        super(x0 - radius, y0 - radius , radius*2, radius*2);
    }
}
