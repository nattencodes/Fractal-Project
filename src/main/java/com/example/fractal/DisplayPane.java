package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class DisplayPane {
    public VBox displayThePain(MandelbrotGenerator mandelbrotGenerator, Slider iterationSlider) {
        VBox displayPane = new VBox();

        Group mandelbrot = mandelbrotGenerator.create();
        int[] iterationValue = new int[]{200};
        iterationSlider.valueProperty().addListener(e -> iterationValue[0] = (int) iterationSlider.getValue());
        iterationSlider.setOnMouseReleased(e -> System.out.println(iterationValue[0]));

        displayPane.getChildren().add(mandelbrot);
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