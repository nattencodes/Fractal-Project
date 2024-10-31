package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class DisplayPane {
    public VBox displayThePain(Slider iterationSlider) {
        VBox displayPane = new VBox();

        int[] iterationValue = new int[]{200};

        MandelbrotGenerator mandelbrotGenerator = new MandelbrotGenerator();
        final Canvas[] mandelbrot = {mandelbrotGenerator.create(iterationValue[0])};

        StackPane pane = new StackPane(mandelbrot[0]);
        pane.setBackground(Background.fill(Color.GREEN));

        mandelbrot[0].setOnScroll(scrollEvent -> {
            mandelbrotGenerator.setXOff((int) scrollEvent.getX());
            mandelbrotGenerator.setYOff((int) scrollEvent.getY());

            mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0]);
            displayPane.getChildren().set(0, mandelbrot[0]);
        });

        // Update mandelbrot set when iteration slider is moved
        iterationSlider.valueProperty().addListener(e -> iterationValue[0] = (int) iterationSlider.getValue());
        iterationSlider.setOnMouseReleased(e ->
            {mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0]);
            displayPane.getChildren().set(0, mandelbrot[0]);});


        displayPane.getChildren().add(pane);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.setSpacing(10);
        displayPane.setPadding(new Insets(10, 10, 10, 10));

        Button button = new Button("download");
        button.setLayoutX(100);
        button.setLayoutY(300);
        displayPane.getChildren().add(button);

        return displayPane;
    }
}