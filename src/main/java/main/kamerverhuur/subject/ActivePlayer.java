package main.kamerverhuur.subject;


import main.kamerverhuur.model.Player;

import java.util.ArrayList;


public class ActivePlayer extends Subject<Player> {
    private Player activePlayer;
    private main.kamerverhuur.Game game;

    public ActivePlayer(main.kamerverhuur.Game game) {
        this.game = game;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
        activePlayer.isturn = true;
        update();
    }

    public void startgame(){
       setActivePlayer(ingeschrijven.get(0));
    }

    public void nextturn(){
        activePlayer.isturn = false;
        update();

        int index = ingeschrijven.indexOf(activePlayer)+1;

        if (index == ingeschrijven.size()){
            index = 0;
        }
        setActivePlayer(ingeschrijven.get(index));
    }

    @Override
    public void update() {
        activePlayer.update(game);
    }

   public ArrayList<Player> Scroreboard(){
       return new ArrayList<>(ingeschrijven);
   }

}
