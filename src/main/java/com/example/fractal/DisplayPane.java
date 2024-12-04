package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPane {
    private final MandelbrotGenerator mandelbrotGenerator = new MandelbrotGenerator();
    private final JuliaGenerator juliaGenerator = new JuliaGenerator();
    private boolean isMandelbrot = true;
    private Canvas currentCanvas;

    public VBox displayThePain(Stage primaryStage, Slider iterationSlider, Slider hueSlider, ParameterPane parameterPane) {
        VBox displayPane = new VBox();

        // Initial values
        int zoom = 300;
        int XMov = 225;
        int YMov = 200;
        String gradient = "Tropical";
        int[] iterationValue = {200}; // Mutable for lambdas

        // Initial canvas setup
        currentCanvas = mandelbrotGenerator.create(iterationValue[0], zoom, YMov, XMov, gradient);
        StackPane canvasPane = new StackPane(currentCanvas); // Wrap canvas in a StackPane

        // Slider listeners
        iterationSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            iterationValue[0] = newValue.intValue();
            updateCanvas(iterationValue[0], hueSlider.getValue(), XMov, YMov, zoom, gradient, canvasPane);
        });

        hueSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            updateCanvas(iterationValue[0], newValue.doubleValue(), XMov, YMov, zoom, gradient, canvasPane);
        });

        // Buttons
        Button toggleGeneratorButton = new Button("Switch Generator");
        toggleGeneratorButton.setOnAction(e -> {
            isMandelbrot = !isMandelbrot;
            System.out.println("Switched to " + (isMandelbrot ? "Mandelbrot" : "Julia") + " Generator");
            updateCanvas(iterationValue[0], hueSlider.getValue(), XMov, YMov, zoom, gradient, canvasPane);
        });

        Button saveButton = new Button("Save Image");
        saveButton.setOnAction(e -> {
            try {
                saveImage(primaryStage, currentCanvas);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        displayPane.getChildren().addAll(canvasPane, toggleGeneratorButton, saveButton);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.setSpacing(10);
        displayPane.setPadding(new Insets(10, 10, 10, 10));

        return displayPane;
    }

    private void updateCanvas(int iterations, double hue, int XMov, int YMov, int zoom, String gradient, StackPane canvasPane) {
        Canvas newCanvas;
        if (isMandelbrot) {
            newCanvas = mandelbrotGenerator.create(iterations, zoom, YMov, XMov, gradient);
        } else {
            newCanvas = juliaGenerator.create(iterations, Color.hsb(hue, 1, 1));
        }

        System.out.println("Updating canvas with " + (isMandelbrot ? "Mandelbrot" : "Julia") + " generator");
        canvasPane.getChildren().setAll(newCanvas); // Replace the canvas in the StackPane
        currentCanvas = newCanvas; // Update the reference to the current canvas
    }

    private void saveImage(Stage primaryStage, Canvas canvas) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Fractal");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(new SnapshotParameters(), writableImage);

            BufferedImage bufferedImage = new BufferedImage((int) writableImage.getWidth(), (int) writableImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int y = 0; y < writableImage.getHeight(); y++) {
                for (int x = 0; x < writableImage.getWidth(); x++) {
                    int argb = writableImage.getPixelReader().getArgb(x, y);
                    bufferedImage.setRGB(x, y, argb);
                }
            }

            ImageIO.write(bufferedImage, "png", file);
        }
    }
}
