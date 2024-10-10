package com.example.fractal;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DisplayPane {
    public VBox displayThePain() {
        VBox displayPane = new VBox();
        //Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/tempimage.png")));

        Button button = new Button("download");
        button.setLayoutX(100);
        button.setLayoutY(300);
        displayPane.getChildren().add(button);

        MandelbrotGenerator generator = new MandelbrotGenerator();
        Group mandelbread = generator.create();

        displayPane.getChildren().add(mandelbread);

        return displayPane;
    }
}