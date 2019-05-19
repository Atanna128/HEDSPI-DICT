module finalform {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires java.datatransfer;
    requires java.desktop;
    opens sample.Controller;
    opens sample;
}