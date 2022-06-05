package main.kamerverhuur.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import main.kamerverhuur.game;
import main.kamerverhuur.model.Player;

public class SpeelbordController {

    @FXML
    private AnchorPane Rootpane;

    @FXML
    private Pane speelbord;

    @FXML
    private Text turn;

    private game Game;
    private Player player;
    private boolean yourturn = false;

    public Player getPlayer() {
        return player;
    }

    public int factor(int aantalvakjes){
        //if (aantalvakjes < 1){return -1;}
        //else if (0 < aantalvakjes && aantalvakjes < 10){return 5000/aantalvakjes;}
        //else if (10 <= aantalvakjes && aantalvakjes < 20){return 7500/aantalvakjes;}
        //else {return 10000/aantalvakjes;}
        return 523 / (aantalvakjes+1);
    }

    public void set(game Game, Player player){
        this.Game = Game;
        this.player = player;
        Game.speelbord.Inschrijven(this);
        Game.getPlayers().Inschrijven(player);
    }

    public void yourturn(boolean yourturn){
        if (yourturn){
            this.yourturn = true;
            turn.setText("jouw beurt");
        }
        else {
            this.yourturn = false;
            turn.setText("");
        }
        //loid();
    }






    public void loid(){
        var Speelbord = Game.speelbord.getSpeelbord();
        speelbord.getChildren().clear();
        for (int x=0; x< Game.speelbord.getMax_X(); x++){
            for (int y=0; y < Game.speelbord.getMax_Y(); y++){
                Speelbord[x][y].teken(speelbord, factor(Game.speelbord.getMax_X()), yourturn);
            }
        }


    }
}