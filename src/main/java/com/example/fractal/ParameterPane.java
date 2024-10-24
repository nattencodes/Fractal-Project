package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ParameterPane {
    final int[] iterationValue = {200};

    Slider slider;

    private void setSlider(Slider slider) {
        this.slider = slider;
    }

    public Slider getSlider() {
        return slider;
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
        parameterPane.getChildren().add(slider1title);

        Slider iterationSlider = new Slider();
        iterationSlider.setMin(0);
        iterationSlider.setMax(300);
        iterationSlider.setValue(50);
        parameterPane.getChildren().add(iterationSlider);
        setSlider(iterationSlider);

        iterationSlider.setValue(iterationValue[0]);

        Label slider2title = new Label("Weirdness Factor");
        parameterPane.getChildren().add(slider2title);

        Slider slider2 = new Slider();
        slider2.setMin(0);
        slider2.setMax(300);
        slider2.setValue(50);
        parameterPane.getChildren().add(slider2);

        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        Button generateButton = new Button("Generate");
        generateButton.setLayoutX(100);
        generateButton.setLayoutY(300);
        generateButton.setOnAction(e -> System.out.println("MIAW"));
        parameterPane.getChildren().add(generateButton);

        return parameterPane;
    }
}
