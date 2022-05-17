package main.kamerverhuur;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Speelbord {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}