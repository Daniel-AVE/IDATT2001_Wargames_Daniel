package edu.ntnu.idatt2001.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

/**
 * ViewSwitcher retrieved from previous project in IDATT1002
 */
public class ViewSwitcher {

    private static Scene scene;

    public static void setScene(Scene scene) {ViewSwitcher.scene = scene;}

    public static void switchTo(edu.ntnu.idatt2001.Scenes.View view) throws IOException {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFileName())));
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
