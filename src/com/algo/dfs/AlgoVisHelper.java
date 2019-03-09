package com.algo.dfs;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class AlgoVisHelper {

    private AlgoVisHelper() {}

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setColor(Graphics2D g2d, Color c) {
        g2d.setColor(c);
    }

    public static void fillRectangle(Graphics2D g2d, int x, int y, int w, int h) {
        Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
        g2d.fill(rect);
    }
}
