package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DisplayPane {
    public VBox displayThePain(Stage primaryStage, Slider iterationSlider) {
        VBox displayPane = new VBox();

        int[] iterationValue = new int[]{100};

        MandelbrotGenerator mandelbrotGenerator = new MandelbrotGenerator();
        final Canvas[] mandelbrot = {mandelbrotGenerator.create(iterationValue[0])};

        StackPane pane = new StackPane(mandelbrot[0]);
        //pane.setBackground(Background.fill(Color.GREEN));


        final int[] initialX = {0};
        final int[] initialY = {0};

        final int[] finalX = {0};
        final int[] finalY = {0};
        pane.setOnDragDetected(e -> {
            initialX[0] = (int) e.getX();
            initialY[0] = (int) e.getX();
        });



        // Update mandelbrot set when iteration slider is moved
        iterationSlider.valueProperty().addListener(e -> iterationValue[0] = (int) iterationSlider.getValue());
        iterationSlider.setOnMouseReleased(e ->
            {mandelbrot[0] = mandelbrotGenerator.create(iterationValue[0]);
                pane.getChildren().set(0, mandelbrot[0]);
            displayPane.getChildren().set(0, pane);
            });


        displayPane.getChildren().add(pane);
        displayPane.setAlignment(Pos.CENTER);
        displayPane.setSpacing(10);
        displayPane.setPadding(new Insets(10, 10, 10, 10));

        Button button = new Button("download");

        button.setOnAction(e -> {
            try {
                saveImage(primaryStage, combineCanvases(mandelbrot));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        button.setLayoutX(100);
        button.setLayoutY(300);
        displayPane.getChildren().add(button);

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