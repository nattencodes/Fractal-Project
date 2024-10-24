package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DisplayPane {
    public VBox displayThePain(Group mandelbrot) {
        VBox displayPane = new VBox();


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