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



    public int factor(int aantalvakjes){
        if (aantalvakjes < 1){return -1;}
        else if (0 < aantalvakjes && aantalvakjes < 10){return 500/aantalvakjes;}
        else if (10 <= aantalvakjes && aantalvakjes < 20){return 750/aantalvakjes;}
        else {return 1000/aantalvakjes;}
    }
}