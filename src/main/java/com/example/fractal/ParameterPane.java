package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ParameterPane {
    private Slider iterSlider;
    private Slider hueSlider;
    private Object selectedGenerator; // The selected fractal generator

    public ParameterPane() {
        this.selectedGenerator = new JuliaGenerator(); // Default to JuliaGenerator
    }

    public Slider getIterSlider() {
        return iterSlider;
    }

    public Slider getHueSlider() {
        return hueSlider;
    }

    public Object getSelectedGenerator() {
        return selectedGenerator;
    }

    public VBox displayParamPane() {
        VBox parameterPane = new VBox();
        parameterPane.setStyle("-fx-padding: 50;");

        Label title = new Label("Fractal Generator");
        title.setStyle("-fx-font-size: 50");
        title.setAlignment(Pos.TOP_LEFT);
        parameterPane.getChildren().add(title);

        Label slider1title = new Label("Iterations");
        iterSlider = new Slider(1, 300, 100); // Default slider values
        parameterPane.getChildren().addAll(slider1title, iterSlider);

        Label slider2title = new Label("Hue");
        hueSlider = new Slider(0, 255, 50);
        parameterPane.getChildren().addAll(slider2title, hueSlider);

        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        return parameterPane;
    }
}
