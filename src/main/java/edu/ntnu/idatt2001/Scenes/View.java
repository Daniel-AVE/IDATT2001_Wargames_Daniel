package edu.ntnu.idatt2001.Scenes;

/**
 * View retrieved from previous project in IDATT1002, and then refactored for this project
 *
 * @author Daniel Evensen
 */
public enum View {
    /**
     * Start view.
     */
    START("start-screen.fxml"),
    /**
     * Home view.
     */
    HOME("home-page.fxml"),
    /**
     * Credits view.
     */
    CREDITS("credits-page.fxml"),
    /**
     * Battle view.
     */
    BATTLE("battle-page.fxml"),
    /**
     * Newarmy view.
     */
    NEWARMY("new-army-page.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

}
