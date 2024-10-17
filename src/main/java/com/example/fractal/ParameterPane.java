package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class ParameterPane {
    public VBox displayParamPane() {
        VBox parameterPane = new VBox();

        MandelbrotGenerator generator = new MandelbrotGenerator();

        Label title = new Label("Fractal Generator");
        title.setAlignment(Pos.CENTER);
        parameterPane.getChildren().add(title);

        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        Button button = new Button("Generate");
        button.setLayoutX(100);
        button.setLayoutY(300);
        parameterPane.getChildren().add(button);

        return parameterPane;
    }
}
