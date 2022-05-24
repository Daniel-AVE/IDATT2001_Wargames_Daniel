package edu.ntnu.idatt2001;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import edu.ntnu.idatt2001.Scenes.View;
import edu.ntnu.idatt2001.Scenes.ViewSwitcher;

import java.io.IOException;

/**
 * The type Wargames main app.
 *
 * @author Daniel Evensen
 */
public class WargamesMainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Pane());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.START);

        //Sets icon for the application bar at the top
        stage.getIcons()
                .add(new Image("file:src/main/resources/edu/ntnu/idatt2001/Images/" +
                        "Logo.png"));

        //Sets application title
        stage.setTitle("Medieval War Games");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts the application with the help of a method called launch() from its parents class Application which
     * ultimately initiates the program
     *
     * @param args main
     */
    public static void main(String[] args) {
        launch();
    }
}