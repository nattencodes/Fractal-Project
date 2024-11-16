package com.example.fractal;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelbrotGenerator {

    private int XOff = 310;
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
        Canvas canvas = new Canvas(450, 400);
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        drawSet(pw, 175, XOff, YOff, iterations);

        return canvas;
    }

    public void drawSet(PixelWriter pw, double zoom, int XOff, int YOff, int iterations) {
        for (int x = -XOff; x < XOff; x++) {
            for (int y = -YOff; y < YOff; y++) {

                double totalIterations = (checkConvergence(x / zoom, y / zoom, iterations));

                // grayscale coloring
                double factor;
                int intensity;
                if (totalIterations == iterations) {
                    pw.setColor(x + XOff, y + YOff, Color.BLACK);
                } else {
                    factor = Math.sqrt(totalIterations / iterations);
                    intensity = (int) Math.round(255 * factor);
                    pw.setColor(x + XOff, y + YOff, Color.rgb(intensity, intensity, intensity));
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
