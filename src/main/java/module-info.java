module com.javafxproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.javafxproject to javafx.fxml;
    exports com.javafxproject;
}