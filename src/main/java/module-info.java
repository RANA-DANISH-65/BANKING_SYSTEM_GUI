module com.example.bank_ease {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.bank_ease to javafx.fxml;
    exports com.example.bank_ease;
}