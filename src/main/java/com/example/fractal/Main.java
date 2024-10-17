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
        DisplayPane displayPane = new DisplayPane();

        vbox1.getChildren().addAll(menuBar, displayPane.displayThePain());
        primaryStage.setScene(scene);
        primaryStage.setTitle("aHHHHHHHHHH GET ME OUT OF HERE");
        primaryStage.show();
    }
}
