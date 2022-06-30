package main.kamerverhuur;

import main.kamerverhuur.model.*;
import main.kamerverhuur.subject.*;
import main.kamerverhuur.subject.Speelbords.Speelbord;


public class Game {
    public Speelbord speelbord;

    private ActivePlayer players = new ActivePlayer(this);

    public ActivePlayer getPlayers() {
        return players;
    }

    public int turn;

    public int getLEFTTurn() {
        if (speelbord.getZetten() == -1){return -1;}
        return (speelbord.getZetten() - turn);
    }

    public void domove(Move move){
        turn++;
        int score = players.getActivePlayer().score(speelbord);
        move.game = this;
        if (CanDoMove( move, players.getActivePlayer())) {
            if (speelbord.domove(move)) {
                if (score == players.getActivePlayer().score(speelbord)) {
                    getPlayers().nextturn();
                }
                if (speelbord.getZetten() == turn) {
                    Player winnar = getwinnar();
                    System.out.println("de winnar is " + winnar.name + " met een score van " + winnar.score(speelbord));
                }
            }
        }
    }
    public Boolean CanDoMove(Move move, Player player){
        if (speelbord.MoveCheck(move.X(), move.Y(), move.move)){
           if (player == players.getActivePlayer()){
               return true;
           }
        }
        return false;
    }

    public Player getwinnar(){
        Player winnar = new Player("", null);
        int highscore = 0;
        for (var player :players.Scroreboard()) {
            if (highscore > player.score(speelbord)){
                winnar = player;
            }
        }
        return winnar;
    }


    public void newGame(Speelbord factory) {
        speelbord = factory;
    }

    public void startgame(){
        players.startgame();
        speelbord.update();
        turn = 0;
    }









}






