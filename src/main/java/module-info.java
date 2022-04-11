module edu.ntnu.idatt2001.idatt2001_wargames_daniel_e {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;

    opens edu.ntnu.idatt2001 to javafx.fxml;
    exports edu.ntnu.idatt2001;
}