package main.kamerverhuur;

import java.lang.reflect.Array;

import javafx.geometry.Point2D;
import main.kamerverhuur.model.*;

public class game {
    private figuren[][] speelbord;
    private int max_X, max_Y;

    public void newgame(figuurs figuur, int X,int Y, boolean Sides) {
        switch (figuur){
            case driehoek: Y = Y*2;
                speelbord = new driehoek[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        if (y % 2 == 0){
                            speelbord[x][y] = new driehoek(x, y);
                        }else {
                            speelbord[x][y] = new driehoek(true, x, y);
                        }
                    }
                }
                if (Sides){setborder_driehoek(X, Y);}
                break;

            case hexagon: speelbord = new hexagon[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        speelbord[x][y] = new hexagon(x, y);
                    }
                }
                if (Sides){setborder_Hexagon(X, Y);}
                break;

            default: speelbord = new vierkant[X][Y];
                for (int x=0; x< X; x++){
                    for (int y=0; y < Y; y++){
                        speelbord[x][y] = new vierkant(x, y);
                    }
                }
                if (Sides){setborder_vierkant(X, Y);}
                break;
        }
        max_X = X;
        max_Y = Y;
    }
    private void setborder_driehoek(int x,int y){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(0,null);
            speelbord[X][y-1].move(0,null);

        }
        for (int Y=0;Y<y;Y++){
            if (Y % 2 == 0){
                speelbord[0][Y].move(2,null);
            }else {
                speelbord[x-1][Y].move(2,null);
            }

        }
    }
    private void setborder_Hexagon(int x,int y){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(1,null);
            speelbord[X][0].move(0,null);
            speelbord[X][0].move(8,null);

            speelbord[X][y-1].move(1,null);
            speelbord[X][y-1].move(2,null);
            speelbord[X][y-1].move(3,null);

        }
        for (int Y=0;Y<y;Y++){
            speelbord[0][Y].move(7,null);
            speelbord[0][Y].move(6,null);
            speelbord[0][Y].move(5,null);

            speelbord[x-1][Y].move(3,null);
            speelbord[x-1][Y].move(4,null);
            speelbord[x-1][Y].move(5,null);

        }
    }
    private void setborder_vierkant(int x,int y){
        for (int X=0; X< x ;X++){
            speelbord[X][0].move(0,null);
            speelbord[X][y-1].move(2,null);

        }
        for (int Y=0;Y<y;Y++){
            speelbord[0][Y].move(3,null);
            speelbord[x-1][Y].move(1,null);

        }
    }


    public Boolean Solidmove(int X, int Y, int move){
        if (max_X >= X && max_Y >= Y){
             return speelbord[X][Y].solidmove(move);
        }
        return false;
    }

    public boolean algedaan_vakje1(int X, int Y, int move)
    {
        if (Solidmove(X, Y, move)) {
                return speelbord[X][Y].algedaan(move);
        }
        return false;
    }
    public boolean algedaanvakje2(int X, int Y, int move)
    {
        if (Solidmove(X, Y, move)) {
                var A = speelbord[X][Y].getvakje2(move);
                X = X + A[0];
                Y = Y + A[1];
                if (max_X <= X && max_Y <= Y) {
                    return speelbord[X][Y].algedaan(speelbord[X][Y].switch_move(move));
                }
        }
        return false;
    }
    public Boolean MoveCheck(int X, int Y, int move){
        if (Solidmove(X, Y, move)) {
            return algedaan_vakje1(X, Y, move) || algedaanvakje2(X, Y, move);
        }
        return false;
    }

    public figuren getfiguur(int X, int y){
        return speelbord[X][y];
    }

}






