package com.example.fractal;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuBar {
    private String currentTheme;
    private Scene scene;
    private String currentFont;

    public MenuBar(Scene scene) {
        this.scene = scene;
        this.currentTheme = "light"; // Default theme
        this.currentFont = "Comic Sans";
    }

    public javafx.scene.control.MenuBar make() {
        javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();

        Menu file = new Menu("File");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(e -> System.exit(0));
        MenuItem newWindow = new MenuItem("New Window");
        newWindow.setOnAction(e -> (new Main()).start(new Stage()));
        file.getItems().addAll(newWindow, quit);

        Menu options = new Menu("Options");
        Menu font = new Menu("font");
        MenuItem comicSans = new MenuItem("Comic Sans");
        MenuItem def = new MenuItem("def");
        Menu theme = new Menu("Change Theme");
        MenuItem light = new MenuItem("Light");
        MenuItem dark = new MenuItem("Dark");


        light.setOnAction(e -> {
            if (!"light".equals(currentTheme)) { // Only switch if the current theme isn't light
                scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("dark-mode.css")).toExternalForm()); // Remove dark theme
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("light-mode.css")).toExternalForm()); // Add light theme
                currentTheme = "light"; // Update the current theme
            }
        });

        // Switch to dark theme
        dark.setOnAction(e -> {
            if (!"dark".equals(currentTheme)) { // Only switch if the current theme isn't dark
                scene.getStylesheets().remove(Objects.requireNonNull(getClass().getResource("light-mode.css")).toExternalForm()); // Remove light theme
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("dark-mode.css")).toExternalForm()); // Add dark theme
                currentTheme = "dark"; // Update the current theme
            }
        });

        theme.getItems().addAll(light, dark);
        font.getItems().addAll(comicSans, def);

        options.getItems().addAll(theme, font);

        Menu help = new Menu("Help");
        MenuItem sendFeedback = new MenuItem("Send Feedback");
        sendFeedback.setOnAction(e -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Send Feedback");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText("We sincerely apologize.");
            codeDialog.setContentText(
                    "We currently do not accept feedback."
            );
            codeDialog.show();
        });
        MenuItem guide = new MenuItem("Guide");
        guide.setOnAction(e -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Simple Guide");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText("How to make it work");
            codeDialog.setContentText(
                    "First, the iteration slider controls how \"detailed\" the fractal is. The higher the value, the more time the image will take to render. Default value is 250.\n" +
                            "Next, the zoom slider, as its name indicates, serves to zoom into the image.\n" +
                            "Finally, the X and Y slider control the panning in the X and Y axis.\n" +
                            "As an addition, you can change the gradient of the fractal in the bottom right corner.\n" +
                            "When you are satisfied, you can save a .png file of the fractal using the download button."
            );
            codeDialog.show();
        });
        help.getItems().addAll(guide, sendFeedback);

        menuBar.getMenus().addAll(file, options, help);
        return menuBar;
    }
}
