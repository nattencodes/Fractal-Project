package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ParameterPane {
    Slider iterSlider;
    Slider hueSlider;
    private void setIterSlider(Slider slider) {
        this.iterSlider = slider;
    }
    private void setHueSlider(Slider slider) {
        this.hueSlider = slider;
    }

    public Slider getIterSlider() {
        return iterSlider;
    }
    public Slider getHueSlider() {
        return hueSlider;
    }

    public VBox displayParamPane() {
        VBox parameterPane = new VBox();
        parameterPane.setStyle("-fx-padding: 50;");

        Label title = new Label("Fractal Generator");
        title.setStyle("-fx-font-size: 50");
        title.setAlignment(Pos.TOP_LEFT);
        parameterPane.getChildren().add(title);

        String textClick = "What is a Mandelbrot Fractal?";

        Label whatsThis = new Label(textClick);
        whatsThis.setStyle("-fx-underline: true;");
        whatsThis.setOnMouseClicked(event -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Information on Fractals");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText(textClick);
            codeDialog.setContentText(
                    """
                            The Mandelbrot set is a specific fractal formed by iterating a simple equation: starting with a complex number, repeatedly squaring it and adding the original number.
                            If the result stays within a fixed boundary, the number is part of the set; otherwise, it’s excluded.
                            Visually, the Mandelbrot set has a central, bulb-like shape with smaller, connected "buds" and intricate, infinitely complex patterns along its edge.
                            Each point on the boundary shows self-similarity, meaning you can zoom in forever, always finding new, smaller versions of the shape.
                            Its distinctive shape is iconic in fractal mathematics and chaos theory."""
            );
            codeDialog.show();
        });
        parameterPane.getChildren().add(whatsThis);

        Label slider1title = new Label("Iterations");

        Slider iterationSlider = new Slider();
        iterationSlider.setMin(0);

        iterationSlider.setMax(300);
        iterationSlider.setValue(50);
        setIterSlider(iterationSlider);

        iterationSlider.setValue(100);

        Label slider2title = new Label("Hue");

        Slider hueSlider = new Slider();
        hueSlider.setMin(0);
        hueSlider.setMax(255);
        hueSlider.setValue(50);
        setHueSlider(hueSlider);


        parameterPane.getChildren().addAll(slider1title, iterationSlider, slider2title, hueSlider);
        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        return parameterPane;
    }
}
