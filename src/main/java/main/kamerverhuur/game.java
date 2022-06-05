package main.kamerverhuur;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javafx.geometry.Point2D;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.model.*;

public class game {
    public speelbord speelbord;
    private static int zetten =0;

    private activePlayer players = new activePlayer(this);

    public activePlayer getPlayers() {
        return players;
    }

    public boolean newgame_test(figuurs figuur, int X, int Y, boolean Sides){
        zetten =0;
        if (X>=1 && Y>=1){
            newgame( figuur,  X, Y,  Sides);
            return true;
        }
        return false;
    }

    public void domove(int X, int Y, int move, Player player){
        if (player == players.getActivePlayer()){
            speelbord.domove( X,  Y,  move,  player, this);

            //System.out.println(players.scoreboard());

        }
    }

    public static void setZetten() {
        zetten--;
    }

    public static int getZetten() {
        return zetten;
    }

    public void newgame(figuurs figuur, int X,int Y, boolean Sides) {
        zetten =0;
        figuren[][] speelbord;

        switch (figuur){
            case driehoek: Y = Y*2;
                speelbord = new driehoek[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        if (y % 2 == 0){
                            zetten += 3;
                            speelbord[x][y] = new driehoek(x, y, this);
                        }else {
                            speelbord[x][y] = new driehoek(true, x, y, this);
                        }
                    }
                }
                zetten += X + Y;
                if (Sides){setborder_driehoek(X, Y, speelbord);}
                break;

            case hexagon: speelbord = new hexagon[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        zetten += 6;
                        speelbord[x][y] = new hexagon(x, y, this);
                    }
                }
                zetten += X + Y;
                if (Sides){setborder_Hexagon(X, Y, speelbord);}
                break;

            default: speelbord = new vierkant[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        zetten += 2;
                        speelbord[x][y] = new vierkant(x, y, this);
                    }
                }
                zetten += X + Y;
                if (Sides){setborder_vierkant(X, Y, speelbord);}
                break;
        }

        this.speelbord = new speelbord(X, Y);
        this.speelbord.setSpeelbord(speelbord);
    }

    public void startgame(){
        players.setActivePlayer(players.ingeschrijven.get(0));
        speelbord.update();
    }



    private void setborder_driehoek(int x,int y, figuren[][] speelbord){
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
    private void setborder_Hexagon(int x,int y, figuren[][] speelbord){
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
    private void setborder_vierkant(int x,int y, figuren[][] speelbord){
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






