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

    public MenuBar(Scene scene) {
        this.scene = scene;
        this.currentTheme = "light"; // Default theme
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
        MenuItem settings = new MenuItem("Settings");
        Menu theme = new Menu("Change Theme");
        MenuItem light = new MenuItem("Light");
        MenuItem dark = new MenuItem("Dark");

        settings.setOnAction(e -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Settings");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText("Settings");
            codeDialog.setContentText(
                    """
                            In construction. Come back soon :P """
            );
            codeDialog.show();
        });

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

        options.getItems().addAll(settings, theme);

        Menu help = new Menu("Help");
        MenuItem sendFeedback = new MenuItem("Send Feedback");
        sendFeedback.setOnAction(e -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Information on Fractals");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText("Hello");
            codeDialog.setContentText(
                    "Bonjour"
            );
            codeDialog.show();
        });
        MenuItem guide = new MenuItem("Guide");
        help.getItems().addAll(guide, sendFeedback);

        menuBar.getMenus().addAll(file, options, help);
        return menuBar;
    }
}
