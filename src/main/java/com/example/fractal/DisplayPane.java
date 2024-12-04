package com.example.fractal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DisplayPane {
    public VBox displayThePain(Stage primaryStage, Slider iterationSlider, Slider zoomSlider, Slider YMovSlider, Slider XMovSlider) {

        VBox displayPane = new VBox();

        int[] iterationValue = new int[]{250};
        int[] zoom = new int[]{150};
        int[] YMovValue = new int[]{200};
        int[] XMovValue = new int[]{300};
        String[] gradient = new String[]{"Grayscale"};

        //MandelbrotGenerator mandelbrotGenerator = new MandelbrotGenerator();
        //final Canvas[] mandelbrot = {mandelbrotGenerator.create(iterationValue[0], colorr.get())};

        JuliaGenerator mandelbrotGenerator = new JuliaGenerator();
        final Canvas[] mandelbrot = {mandelbrotGenerator.create(iterationValue[0], colorr.get())};

        StackPane pane = new StackPane(mandelbrot[0]);

        ComboBox<String> gradientChoice = new ComboBox<>();
        gradientChoice.setValue("Grayscale");
        gradientChoice.getItems().add("Grayscale");
        gradientChoice.getItems().add("Tropical");

        gradientChoice.setOnAction(e -> {
            gradient[0] = gradientChoice.getValue();
            mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0], zoom[0], YMovValue[0], XMovValue[0], gradient[0]);
            pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
        });

        zoomSlider.valueProperty().addListener(e -> zoom[0] = (int) zoomSlider.getValue());
        zoomSlider.setOnMouseReleased(e -> {
            mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0], zoom[0], YMovValue[0], XMovValue[0], gradient[0]);
            pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
        });

        // Update mandelbrot set when iteration slider is moved
        iterationSlider.valueProperty().addListener(e -> iterationValue[0] = (int) iterationSlider.getValue());
        iterationSlider.setOnMouseReleased(e ->
            {mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0], zoom[0], YMovValue[0], XMovValue[0], gradient[0]);
                pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
            });

        YMovSlider.valueProperty().addListener(e -> YMovValue[0] = (int) YMovSlider.getValue());
        YMovSlider.setOnMouseReleased(e ->
        {mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0], zoom[0], YMovValue[0], XMovValue[0], gradient[0]);
            pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
        });

        XMovSlider.valueProperty().addListener(e -> XMovValue[0] = (int) XMovSlider.getValue());
        XMovSlider.setOnMouseReleased(e ->
        {mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0], zoom[0], YMovValue[0], XMovValue[0], gradient[0]);
            pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
        });



        displayPane.getChildren().add(pane);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.setSpacing(10);
        displayPane.setPadding(new Insets(10, 10, 10, 10));

        Button button = new Button("Download");

        button.setOnAction(e -> {
            try {
                saveImage(primaryStage, combineCanvases(mandelbrot));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setLayoutX(100);
        button.setLayoutY(300);
        displayPane.getChildren().addAll(button, gradientChoice);

        return displayPane;
    }

    public void saveImage(Stage primaryStage, Canvas canvas) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Fractal");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Image", "*.png"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if (file != null) {
            // image from canvas
            WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
            canvas.snapshot(new SnapshotParameters(), writableImage);

            // get data from canvas
            PixelReader pixelReader = writableImage.getPixelReader();
            BufferedImage bufferedImage = new BufferedImage((int) writableImage.getWidth(), (int) writableImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // pixel data to BufferedImage
            for (int y = 0; y < writableImage.getHeight(); y++) {
                for (int x = 0; x < writableImage.getWidth(); x++) {
                    Color color = pixelReader.getColor(x, y);
                    int argb = ((int)(color.getOpacity() * 255) << 24) |
                            ((int)(color.getRed() * 255) << 16) |
                            ((int)(color.getGreen() * 255) << 8) |
                            (int)(color.getBlue() * 255);
                    bufferedImage.setRGB(x, y, argb);
                }
            }

            // write BufferedImage to file
            ImageIO.write(bufferedImage, "png", file);
        }
    }


    public Canvas combineCanvases(Canvas[] canvasArray) {
        if (canvasArray == null || canvasArray.length == 0) return null;

        double maxWidth = 0;
        double maxHeight = 0;
        for (Canvas canvas : canvasArray) {
            maxWidth = Math.max(maxWidth, canvas.getWidth());
            maxHeight += canvas.getHeight();
        }

        Canvas combinedCanvas = new Canvas(maxWidth, maxHeight);
        GraphicsContext gc = combinedCanvas.getGraphicsContext2D();

        double currentY = 0;
        for (Canvas canvas : canvasArray) {
            gc.drawImage(canvas.snapshot(null, null), 0, currentY);
            currentY += canvas.getHeight();
        }

        return combinedCanvas;
    }

}