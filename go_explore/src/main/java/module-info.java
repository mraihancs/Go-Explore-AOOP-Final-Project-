module com.example.go_explore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.go_explore to javafx.fxml;
    exports com.example.go_explore;
}