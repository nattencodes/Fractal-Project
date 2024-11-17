package com.example.fractal;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MandelbrotGenerator {
    public Canvas create(int iterations, int zoom, int YMov) {
        Canvas canvas = new Canvas(450, 400);
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();
        int XOff = 225;
        drawSet(pw, zoom, XOff, YMov, iterations);

        return canvas;
    }

    public void drawSet(PixelWriter pw, double zoom, int XOff, int YOff, int iterations) {
        double cx, cy;
        for (int x = 0; x < 450; x++) {
            for (int y = 0; y < 400; y++) {
                cx = ((x - XOff) / zoom);
                cy = ((y - YOff) / zoom);
                int totalIterations = (checkConvergence(cx, cy, iterations));

                // grayscale coloring
//                double factor;
//                int intensity;
//                if (totalIterations == iterations) {
//                    pw.setColor(x , y, Color.BLACK);
//                } else {
//                    factor = Math.sqrt((double) totalIterations / iterations);
//                    intensity = (int) Math.round(255 * factor);
//                    pw.setColor(x, y, Color.rgb(intensity, intensity, intensity));
//                }

                // gradient 1 coloring
                int i;
                Color[] mapping = new Color[16];
                mapping[0] = Color.rgb(66, 30, 15);
                mapping[1] = Color.rgb(25, 7, 26);
                mapping[2] = Color.rgb(9, 1, 47);
                mapping[3] = Color.rgb(4, 4, 73);
                mapping[4] = Color.rgb(0, 7, 100);
                mapping[5] = Color.rgb(12, 44, 138);
                mapping[6] = Color.rgb(24, 82, 177);
                mapping[7] = Color.rgb(57, 125, 209);
                mapping[8] = Color.rgb(134, 181, 229);
                mapping[9] = Color.rgb(211, 236, 248);
                mapping[10] = Color.rgb(241, 233, 191);
                mapping[11] = Color.rgb(248, 201, 95);
                mapping[12] = Color.rgb(255, 170, 0);
                mapping[13] = Color.rgb(204, 128, 0);
                mapping[14] = Color.rgb(153, 87, 0);
                mapping[15] = Color.rgb(106, 52, 3);

                if (totalIterations == iterations) {
                    pw.setColor(x, y, Color.BLACK);
                } else {
                    i = totalIterations % 16;
                    pw.setColor(x, y, mapping[i]);
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
