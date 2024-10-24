package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ParameterPane {
    final int[] iterationValue = {200};

    public VBox displayParamPane(MandelbrotGenerator mandelbrotGenerator) {
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
            codeDialog.setHeight(300);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText(textClick);
            codeDialog.setContentText(
                    """
                            A mandelbrot fractal is a fractal made of the mandelbrot set.\s
                            When it is mathematically visualised in a 2D coordinate plane, \s"""
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

        iterationSlider.setValue(iterationValue[0]);
        iterationSlider.valueProperty().addListener(e -> iterationValue[0] = (int) iterationSlider.getValue());
        iterationSlider.setOnMouseReleased(e -> {mandelbrotGenerator.setIterations(iterationValue[0]);
                                                 });

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
