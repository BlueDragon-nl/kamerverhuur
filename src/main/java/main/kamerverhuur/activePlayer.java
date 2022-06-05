package main.kamerverhuur;


import main.kamerverhuur.model.Player;

public class activePlayer extends Observer<Player>{
    private Player activePlayer;
    private game game;

    public activePlayer(main.kamerverhuur.game game) {
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
        for (var controller :game.speelbord.ingeschrijven) {
            if (controller.getPlayer() == activePlayer){
                    controller.yourturn(activePlayer.isturn);
            }
        }
    }

    public String scoreboard(){
        String scoreboard = "";
        for (var item :ingeschrijven) {
            scoreboard += "scrore van "+item.name +" "+ item.score(game.speelbord)+"\n";
        }
        return scoreboard;
    }
}
