module com.example.lca {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lca to javafx.fxml;
    exports com.example.lca;
}