package com.example.fractal;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class MenuBar {
    public javafx.scene.control.MenuBar make() {
        javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();

        Menu file = new Menu("File");
        MenuItem quit = new MenuItem("Quit");
        MenuItem newWindow = new MenuItem("New Window");
        file.getItems().addAll(newWindow, quit);

        Menu options = new Menu("Options");
        MenuItem settings = new MenuItem("Settings");
        Menu theme = new Menu("Change Theme");
        MenuItem light = new MenuItem("Light");
        MenuItem dark = new MenuItem("Dark");
        theme.getItems().addAll(light, dark);

        options.getItems().addAll(settings, theme);

        Menu help = new Menu("Help");
        MenuItem sendFeedback = new MenuItem("Send Feedback");
        MenuItem guide = new MenuItem("Guide");
        help.getItems().addAll(guide, sendFeedback);

        menuBar.getMenus().addAll(file, options, help);
        return menuBar;
    }
}
