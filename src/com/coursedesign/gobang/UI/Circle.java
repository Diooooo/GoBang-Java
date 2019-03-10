package com.coursedesign.gobang.UI;

import java.awt.geom.Ellipse2D;

/**
 * Created by lenovo on 2016/10/9.
 */
public class Circle {
    private Ellipse2D circle;
    private int radius;
    private int positionX;
    private int positionY;
    private int type;

    public Circle(int positionX, int positionY, int radius, int type) {
        this.radius = radius;
        this.positionX = positionX;
        this.positionY = positionY;
        this.type = type;
        this.circle = new Ellipse2D.Double(positionX, positionY, radius, radius);
    }

    public Ellipse2D getCircle() {
        return circle;
    }

    public int getType() {
        return type;
    }
}
