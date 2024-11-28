package com.example.fractal;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ParameterPane {
    Slider iterSlider;
    Slider hueSlider;
    JuliaGenerator julia = new JuliaGenerator();
    MandelbrotGenerator mandel = new MandelbrotGenerator();

    private void setIterSlider(Slider slider) {
        this.iterSlider = slider;
    }
    private void setHueSlider(Slider slider) {
        this.hueSlider = slider;
    }

    public Slider getIterSlider() {
        return iterSlider;
    }
    public Slider getHueSlider() {
        return hueSlider;
    }
    public JuliaGenerator getJuliaGenerator() {
        return julia;
    }
    public MandelbrotGenerator getMandelbrotGenerator() {
        return mandel;
    }

    public VBox displayParamPane() {
        VBox parameterPane = new VBox();
        parameterPane.setStyle("-fx-padding: 50;");

        Label title = new Label("Fractal Generator");
        title.setStyle("-fx-font-size: 50");
        title.setAlignment(Pos.TOP_LEFT);
        parameterPane.getChildren().add(title);

        String textClick = "What is a Fractal?";

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

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Julia Set");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Mandelbrot Set");
        rb2.setToggleGroup(group);
        rb2.setPadding(new Insets(0,0,20,0));

        parameterPane.getChildren().addAll(rb1, rb2, whatsThis);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if (group.getSelectedToggle().getUserData() != null) {
                    System.out.println("Selected Radio Button is " + group.getUserData().toString());
                    System.out.println(group.getSelectedToggle().toString());
                } else {
                    System.out.println("Selected Radio Button is null");
                    String thingy = group.getSelectedToggle().toString();
                    String soup = thingy.substring(46,(thingy.length()-4));
                    if (soup.length()<9) {
                        System.out.println("Julia");
                    } else {
                        System.out.println("Mandelbrot");
                    }
                }
            }
        });


        Label slider1title = new Label("Iterations");

        Slider iterationSlider = new Slider();
        iterationSlider.setMin(0);

        iterationSlider.setMax(300);
        iterationSlider.setValue(50);
        setIterSlider(iterationSlider);

        iterationSlider.setValue(100);

        Label slider2title = new Label("Hue");

        Slider hueSlider = new Slider();
        hueSlider.setMin(0);
        hueSlider.setMax(255);
        hueSlider.setValue(50);
        setHueSlider(hueSlider);


        parameterPane.getChildren().addAll(slider1title, iterationSlider, slider2title, hueSlider);
        parameterPane.setAlignment(Pos.CENTER);
        parameterPane.setSpacing(10);
        parameterPane.setPadding(new Insets(10, 10, 10, 10));

        return parameterPane;
    }
}
