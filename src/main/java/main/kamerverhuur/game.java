package main.kamerverhuur;

import main.kamerverhuur.model.*;
import main.kamerverhuur.subject.*;


public class game {
    public speelbord speelbord;

    private activePlayer players = new activePlayer(this);

    public activePlayer getPlayers() {
        return players;
    }

    public int turn;

    public boolean newgame_test(figuurs figuur, int X, int Y, boolean Sides){
        if (X>=1 && Y>=1){
            newgame( figuur,  X, Y,  Sides);
            return true;
        }
        return false;
    }

    public void domove(Move move){
        turn++;
        int score = players.getActivePlayer().score(speelbord);
        if (speelbord.domove(new Move(move.X,  move.Y,  move.move,  move.activeplayer, this))){
                if (score == players.getActivePlayer().score(speelbord)){
                    getPlayers().nextturn();
                }
                if (speelbord.getZetten() == turn){
                    Player winnar = getwinnar();
                    System.out.println("de winnar is " +winnar.name + " met een score van "+ winnar.score(speelbord));
                }
            }
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



    public void newgame(figuurs figuur, int X,int Y, boolean Sides) {
        main.kamerverhuur.model.figuur[][] speelbord;

        switch (figuur){
            case driehoek: Y = Y*2;
                speelbord = new driehoek[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        if (y % 2 == 0){
                            speelbord[x][y] = new driehoek(x, y, this);
                        }else {
                            speelbord[x][y] = new driehoek(true, x, y, this);
                        }
                    }
                }
                if (Sides){setborder_driehoek(X, Y, speelbord);}
                break;

            case hexagon: speelbord = new hexagon[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        speelbord[x][y] = new hexagon(x, y, this);
                    }
                }
                if (Sides){setborder_Hexagon(X, Y, speelbord);}
                break;

            default: speelbord = new vierkant[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        speelbord[x][y] = new vierkant(x, y, this);
                    }
                }
                if (Sides){setborder_vierkant(X, Y, speelbord);}
                break;
        }

        this.speelbord = new speelbord(X, Y);
        this.speelbord.setSpeelbord(speelbord);
    }

    public void startgame(){
        players.startgame();
        speelbord.update();
        turn = 0;
    }



    private void setborder_driehoek(int x,int y, figuur[][] speelbord){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(0,null);
            speelbord[X][y-1].move(0,null);

        }
        for (int Y=1;Y<y;Y++){
            if (Y % 2 == 0){
                speelbord[0][Y-1].move(1,null);
            }else {
                speelbord[x-1][Y-1].move(1,null);
            }

        }
        speelbord[0][y-1].move(1,null);
    }
    private void setborder_Hexagon(int x,int y, figuur[][] speelbord){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(1,null);
            speelbord[X][0].move(0,null);
            speelbord[X][0].move(7,null);

            speelbord[X][y-1].move(3,null);
            speelbord[X][y-1].move(4,null);
            speelbord[X][y-1].move(5,null);

        }
        for (int Y=0;Y<y;Y++){
            speelbord[0][Y].move(7,null);
            speelbord[0][Y].move(6,null);
            speelbord[0][Y].move(5,null);

            speelbord[x-1][Y].move(1,null);
            speelbord[x-1][Y].move(2,null);
            speelbord[x-1][Y].move(3,null);

        }
    }
    private void setborder_vierkant(int x,int y, figuur[][] speelbord){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(0,null);
            speelbord[X][y-1].move(2,null);

        }
        for (int Y=0;Y<y;Y++){
            speelbord[0][Y].move(3,null);
            speelbord[x-1][Y].move(1,null);

        }
    }


}






