package main.kamerverhuur.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.kamerverhuur.game;
import main.kamerverhuur.model.Move;
import main.kamerverhuur.model.Player;

public class SpeelbordController {


    @FXML
    private AnchorPane Rootpane;

    @FXML
    private VBox name;

    @FXML
    private VBox punten;

    @FXML
    private Pane speelbord;

    @FXML
    private Text speller;

    @FXML
    private Text turn;

    @FXML
    private Text turnsleft;


    private game Game;
    private Player player;
    private boolean yourturn = false;

    public Player getPlayer() {
        return player;
    }

    public int factor(int aantalvakjes){
        if (aantalvakjes < 1){return -1;}
        else if (0 < aantalvakjes && aantalvakjes < 50){return 500/aantalvakjes;}
        else if (50 <= aantalvakjes && aantalvakjes < 100){return 1000/aantalvakjes;}
        else {return 20000/aantalvakjes;}
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
    }


    private void setscoreboard(){
        name.getChildren().clear();
        punten.getChildren().clear();
        for (Player player :Game.getPlayers().Scroreboard()) {
            name.getChildren().add(new Text(player.name));
            punten.getChildren().add(new Text("" +player.score(Game.speelbord)));
        }
    }
    private void setTurnsleft(){
        turnsleft.setText("" + (Game.speelbord.getZetten() - Game.turn));
    }
    private void setSpeelbord(){
        speelbord.getChildren().clear();
        for (int x=0; x< Game.speelbord.getMax_X(); x++){
            for (int y=0; y < Game.speelbord.getMax_Y(); y++){
                Game.speelbord.getfiguur(x,y).teken(speelbord, factor(Game.speelbord.getMax_X()), factor(Game.speelbord.getMax_Y()), this);
            }
        }
    }

    public void domove(Move move){
       if (yourturn){
           move.activeplayer = player;
           Game.domove(move);
       }
    }


    public void loid(){
        setscoreboard();
        setTurnsleft();
        setSpeelbord();
    }
}