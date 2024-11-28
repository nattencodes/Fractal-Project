package com.example.fractal;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class JuliaGenerator {

    private int XOff = 300;
    private int YOff = 200;

    public void setXOff(int XOff) {
        this.XOff = XOff;
    }

    public int getXOff() {
        return this.XOff;
    }

    public void setYOff(int YOff) {
        this.YOff = YOff;
    }

    public int getYOff() {
        return this.YOff;
    }

    public Canvas create(int iterations, Color color) {
        Canvas canvas = new Canvas(400, 400);
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        drawSet(pw, 200, XOff, YOff, iterations, color);

        return canvas;
    }

    public void drawSet(PixelWriter pw, double zoom, int XOff, int YOff, int iterations, Color color) {
        for (int x = -XOff; x < XOff + 200; x++) {
            for (int y = -YOff; y < YOff + 200; y++) {
                if (checkConvergence(x / zoom, y / zoom, iterations) == iterations) {
                    pw.setColor(x + XOff, y + YOff, color);
                }
            }
        }
    }

    public int checkConvergence(double x, double y, int iterations) {
        double z = x; // initial z is the pixel coordinate
        double zi = y;
        double cX = -0.7;    // !!! Placeholder
        double cY = 0.27015; // !!! Placeholder

        for (int i = 0; i < iterations; i++) {
            double z2 = z * z - zi * zi + cX;
            double zi2 = 2 * z * zi + cY;
            z = z2;
            zi = zi2;
            if (z * z + zi * zi >= 4) {
                return i;
            }
        }
        return iterations;
    }
}
