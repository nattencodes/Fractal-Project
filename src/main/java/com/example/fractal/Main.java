package com.example.fractal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        ParameterPane parameterPane = new ParameterPane();
        HBox pains = new HBox(parameterPane.displayParamPane(), displayPane.displayThePain(generator));
        vbox1.getChildren().addAll(pains);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("iconforprog.jpg"))));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fractal Generator");
        primaryStage.show();
    }
}