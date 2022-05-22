module edu.ntnu.idatt2001.idatt2001_wargames_daniel_e {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;

    opens edu.ntnu.idatt2001 to javafx.fxml;
    exports edu.ntnu.idatt2001;
    opens edu.ntnu.idatt2001.Army;
    opens edu.ntnu.idatt2001.IO;
    opens edu.ntnu.idatt2001.Battles;
    opens edu.ntnu.idatt2001.Terrain;
    opens edu.ntnu.idatt2001.Unit_Factory;
    opens edu.ntnu.idatt2001.Units;
    exports edu.ntnu.idatt2001.controllers;
    opens edu.ntnu.idatt2001.controllers to javafx.fxml;
}