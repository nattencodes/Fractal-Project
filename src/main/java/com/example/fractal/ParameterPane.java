package com.example.fractal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ParameterPane {
    private Slider iterSlider;
    private Slider zoomSlider;
    private Slider YMovSlider;
    private Slider XMovSlider;

    private void setIterSlider(Slider slider) {
        this.iterSlider = slider;
    }
    private void setZoomSlider(Slider slider) {
        this.zoomSlider = slider;
    }
    private void setYMovSlider(Slider slider) {
        this.YMovSlider = slider;
    }
    private void setXMovSlider(Slider slider) {
        this.XMovSlider = slider;
    }

    public Slider getIterSlider() {
        return iterSlider;
    }
    public Slider getZoomSlider() {
        return zoomSlider;
    }
    public Slider getYMovSlider() {
        return YMovSlider;
    }
    public Slider getXMovSlider() {
        return XMovSlider;
    }

    public VBox displayParamPane() {
        VBox parameterPane = new VBox();
        parameterPane.setStyle("-fx-padding: 50;");

        Label title = new Label("Fractal Generator");
        title.setStyle("-fx-font-size: 50");
        title.setAlignment(Pos.TOP_LEFT);
        parameterPane.getChildren().add(title);

        String textClick = "What is a Mandelbrot Fractal?";

        Label whatsThis = new Label(textClick);
        whatsThis.setStyle("-fx-underline: true;");
        whatsThis.setOnMouseClicked(event -> {
            Alert codeDialog = new Alert(Alert.AlertType.INFORMATION);
            codeDialog.setTitle("Information on Fractals");
            codeDialog.setHeight(350);
            codeDialog.setWidth(500);
            codeDialog.setHeaderText(textClick);
            codeDialog.setContentText(
                    """
                            The Mandelbrot set is a specific fractal formed by iterating a simple equation: starting with a complex number, repeatedly squaring it and adding the original number.
                            If the result stays within a fixed boundary, the number is part of the set; otherwise, it’s excluded.
                            Visually, the Mandelbrot set has a central, bulb-like shape with smaller, connected "buds" and intricate, infinitely complex patterns along its edge.
                            Each point on the boundary shows self-similarity, meaning you can zoom in forever, always finding new, smaller versions of the shape.
                            Its distinctive shape is iconic in fractal mathematics and chaos theory."""
            );
            codeDialog.show();
        });
        parameterPane.getChildren().add(whatsThis);

        Label slider1title = new Label("Iterations");

        Slider iterationSlider = new Slider();
        iterationSlider.setMin(0);

        iterationSlider.setMax(300);
        iterationSlider.setValue(50);
        setIterSlider(iterationSlider);

        iterationSlider.setValue(100);

        Label slider2title = new Label("Zoom");

        Slider zoomSlider = new Slider();
        zoomSlider.setMin(0);
        zoomSlider.setMax(5000);
        zoomSlider.setValue(0);
        setZoomSlider(zoomSlider);

        Slider YMovSlider = new Slider();
        YMovSlider.setMin(0);
        YMovSlider.setMax(5000);
        YMovSlider.setValue(200);
        setYMovSlider(YMovSlider);

        Slider XMovSlider = new Slider();
        XMovSlider.setMin(0);
        XMovSlider.setMax(8000);
        XMovSlider.setValue(300);
        setXMovSlider(XMovSlider);

        parameterPane.getChildren().addAll(slider1title, iterationSlider, slider2title, zoomSlider, new Label("Move Y"), YMovSlider, new Label("Move X"), XMovSlider);
        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        return parameterPane;
    }
}
