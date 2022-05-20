module edu.ntnu.idatt2001.idatt2001_wargames_daniel_e {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;

    opens edu.ntnu.idatt2001 to javafx.fxml;
    exports edu.ntnu.idatt2001;
}