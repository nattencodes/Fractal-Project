package com.example.fractal;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelbrotGenerator {

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

    public Canvas create(int iterations, int WTFFactor) {
        Canvas canvas = new Canvas(400, 400);
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        drawSet(pw, 200, XOff, YOff, iterations);

        return canvas;
    }

    public void drawSet(PixelWriter pw, double zoom, int XOff, int YOff, int iterations) {
        for (int x = -XOff; x < XOff+200; x++) {
            for (int y = -YOff; y < YOff+200; y++) {

                double totalIterations = (checkConvergence(x / zoom, y / zoom, iterations));
                if (totalIterations == iterations) {
                    pw.setColor(x + XOff, y + YOff, Color.BLACK);
                } else {
                    pw.setColor(x + XOff, y + YOff, Color.hsb(totalIterations, 1, 1));
                }
            }
        }
    }

    public int checkConvergence(double x, double y, int iterations) {
        double z = 0;
        double zi = 0;
        for (int i = 0; i < iterations; i++) {
            double z2 = z * z - (zi * zi);
            double zi2 = 2 * (z * zi);
            z = z2 + x;
            zi = zi2 + y;
            if (z * z + zi * zi >= 3) {
                return i;
            }
        }
        return iterations;
    }
}
