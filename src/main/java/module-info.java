module com.example.fractal {
    requires javafx.fxml;
    requires javafx.controls;


    opens com.example.fractal to javafx.fxml;
    exports com.example.fractal;
}