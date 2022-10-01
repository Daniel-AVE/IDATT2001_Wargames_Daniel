package edu.ntnu.idatt2001.Scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

/**
 * ViewSwitcher retrieved from previous project in IDATT1002
 *
 * @author Daniel Evensen
 */
public class ViewSwitcher {

    private static Scene scene;

    /**
     * Sets scene.
     *
     * @param scene the scene
     */
    public static void setScene(Scene scene) {ViewSwitcher.scene = scene;}

    /**
     * Switch to whatever is defined from class View
     *
     * tries to set root instance of Parent, where it uses FXMLLoader to load an object. Requires non null
     *
     * catches exception if it fails to set root, and change scene according to root
     *
     * @param view the view
     * @throws IOException the io exception
     */
    public static void switchTo(edu.ntnu.idatt2001.Scenes.View view) throws IOException {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ViewSwitcher.class.getResource(view.getFileName())));
            scene.setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
