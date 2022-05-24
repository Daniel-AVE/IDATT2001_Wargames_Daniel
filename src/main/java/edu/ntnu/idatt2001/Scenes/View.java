package edu.ntnu.idatt2001.Scenes;

/**
 * View retrieved from previous project in IDATT1002, and then refactored for this project
 */
public enum View {
    START("start-screen.fxml"),
    HOME("home-page.fxml"),
    CREDITS("credits-page.fxml"),
    BATTLE("battle-page.fxml"),
    NEWARMY("new-army-page.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
