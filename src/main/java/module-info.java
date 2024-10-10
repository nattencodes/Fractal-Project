module com.example.fractal {
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.fractal to javafx.fxml;
    exports com.example.fractal;
}