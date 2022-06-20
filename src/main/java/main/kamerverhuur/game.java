package main.kamerverhuur;

import main.kamerverhuur.model.*;
import main.kamerverhuur.subject.*;


public class game {
    public speelbord speelbord;

    private activePlayer players = new activePlayer(this);
    public int sides;

    public activePlayer getPlayers() {
        return players;
    }

    public int turn;

    public int getLEFTTurn() {
        if (speelbord.getZetten() == -1){return -1;}
        return (speelbord.getZetten() - turn - sides);
    }

    public void domove(Move move){
        turn++;
        int score = players.getActivePlayer().score(speelbord);
        move.Game = this;
        if (speelbord.domove(move)){
                if (score == players.getActivePlayer().score(speelbord)){
                    getPlayers().nextturn();
                }
                if (speelbord.getZetten() == turn){
                    Player winnar = getwinnar();
                    System.out.println("de winnar is " +winnar.name + " met een score van "+ winnar.score(speelbord));
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

    public game(int X,int Y){
        this.speelbord = new speelbord(X, Y);
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
    
    public void newgame(figuurs figuur, int X, int Y, boolean Sides) {
        this.speelbord = new speelbord(X, Y);
        newgame(figuur,  Sides);
    }
    public void newgame(figuurs figuur, boolean Sides) {
        figuur[][] speelveld;
        int X = this.speelbord.getMax_X();
        int Y = this.speelbord.getMax_Y();
        this.sides = 0;
        if (X == 0 || Y == 0){
            this.speelbord.setSpeelbord(new figuur[][]{});
            return;
        }

        switch (figuur){
            case driehoek:
                speelveld = speelvelddriehoek(X, Y*2);
                if (Sides){setborder_driehoek(X, Y*2, speelveld);
                    sides = (X + Y) *2;
                }
                break;

            case hexagon:
                speelveld = speelveldHexagon(X, Y);
                if (Sides){setborder_Hexagon(X, Y, speelveld);
                    sides = ((X + Y) * 3 -2) * 2 ;
                }
                break;

            default: speelveld = speelveldvierkant(X,Y);

                if (Sides){setborder_vierkant(X, Y, speelveld);
                    sides = (X + Y) *2;
                }
                break;
        }

        this.speelbord.setSpeelbord(speelveld);
    }

    public void startgame(){
        players.startgame();
        speelbord.update();
        turn = 0;
    }

    private driehoek[][] speelvelddriehoek(int X, int Y){
        driehoek[][] speelveld = new driehoek[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                if (y % 2 == 0){
                    speelveld[x][y] = new driehoek(x, y, this);
                }else {
                    speelveld[x][y] = new driehoek(true, x, y, this);
                }
            }
        }
        return speelveld;
    }
    private hexagon[][] speelveldHexagon(int X, int Y){
        hexagon[][]  speelveld = new hexagon[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                speelveld[x][y] = new hexagon(x, y, this);
            }
        }
        return speelveld;
    }

    private vierkant[][] speelveldvierkant(int X, int Y){
        vierkant[][] speelveld = new vierkant[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                speelveld[x][y] = new vierkant(x, y, this);
            }
        }
        return speelveld;
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






