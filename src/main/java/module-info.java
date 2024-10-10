module com.example.fractal {
    requires javafx.fxml;


    opens com.example.fractal to javafx.fxml;
    exports com.example.fractal;
}