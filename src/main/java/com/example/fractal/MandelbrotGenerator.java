package com.example.fractal;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MandelbrotGenerator {
    public Group create(int iterations) {
        StackPane rootPane = new StackPane();
        rootPane.setStyle("-fx-background-color: green");
        Group root = new Group(rootPane);

        Canvas canvas = new Canvas(400, 400);
        PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

        drawSet(pw, 200, 300, 200, iterations);
        rootPane.getChildren().add(canvas);

        return root;
    }

    public void drawSet(PixelWriter pw, double zoom, double left, double right, int iterations) {
        for (int x = -((int) left); x < 200; x++) {
            for (int y = -200; y < 200; y++) {
                if (checkConvergence(x / zoom, y / zoom, iterations) == iterations) {
                    pw.setColor(x + ((int) left), y + 200, Color.HOTPINK);
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
