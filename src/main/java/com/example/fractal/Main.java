package com.example.fractal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    public void start(Stage primaryStage) {
        VBox vbox1 = new VBox();
        Scene scene = new Scene(vbox1);

        MandelbrotGenerator generator = new MandelbrotGenerator();

        javafx.scene.control.MenuBar menuBar = new MenuBar(scene).make();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("light-mode.css")).toExternalForm());


        vbox1.getChildren().addAll(menuBar);
        DisplayPane displayPane = new DisplayPane();
        ParameterPane makeParameterPane = new ParameterPane();
        VBox parameterPane = makeParameterPane.displayParamPane();

        Slider iterationSlider = makeParameterPane.getSlider();

        HBox pains = new HBox(parameterPane, displayPane.displayThePain(generator, iterationSlider));
        vbox1.getChildren().addAll(pains);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fractal Generator");
        primaryStage.show();
    }
}