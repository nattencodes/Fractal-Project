package com.example.fractal;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DisplayPane {
    public VBox displayThePain() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/fractal.png")));
        
    }
}