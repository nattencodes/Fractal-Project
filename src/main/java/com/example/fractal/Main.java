package com.example.fractal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage) {
        VBox vbox1 = new VBox();
        Scene scene = new Scene(vbox1);

        javafx.scene.control.MenuBar menuBar = new MenuBar().make();

        vbox1.getChildren().addAll(menuBar);
        //DisplayPane displayPane = new DisplayPane();
        //vbox1.getChildren().add(displayPane.displayThePain());
        ParameterPane parameterPane = new ParameterPane();
        vbox1.getChildren().add(parameterPane.displayParamPane());
        primaryStage.setScene(scene);
        primaryStage.setTitle("aHHHHHHHHHH GET ME OUT OF HERE");
        primaryStage.show();
    }
}
