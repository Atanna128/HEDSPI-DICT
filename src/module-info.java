module finalform {
    requires javafx.fxml;
    requires javafx.controls;
    requires kotlin.stdlib;
    requires java.datatransfer;
    requires java.desktop;
    opens sample.Controller;
    opens sample;
}