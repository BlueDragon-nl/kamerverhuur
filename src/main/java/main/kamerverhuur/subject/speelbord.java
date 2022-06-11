package main.kamerverhuur.subject;

import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.game;
import main.kamerverhuur.model.Player;
import main.kamerverhuur.model.figuren;

public class speelbord extends subject<SpeelbordController> {

    private figuren[][] speelbord;
    private int max_X, max_Y;

    public speelbord(int max_X, int max_Y){
        this.max_X = max_X;
        this.max_Y = max_Y;
    }



    public void setSpeelbord(figuren[][] speelbord) {
        this.speelbord = speelbord;
        update();
    }



    public int getMax_X() {
        return max_X;
    }

    public int getMax_Y() {
        return max_Y;
    }

    public Boolean Solidmove(int X, int Y, int move){
        if (max_X >= X && max_Y >= Y){
            return speelbord[X][Y].solidmove(move);
        }
        return false;
    }
    public boolean algedaan_vakje1(int X, int Y, int move) {
        if (Solidmove(X, Y, move)) {
            return speelbord[X][Y].algedaan(move);
        }
        return false;
    }
    public boolean algedaanvakje2(int X, int Y, int move) {
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

    public Boolean domove(int X, int Y, int move, Player player, game Game){
        if (MoveCheck(X, Y, move)){
            speelbord[X][Y].move(move, player);

            var A = getfiguur(X,Y).getvakje2(move);

            X = X + A[0];
            Y = Y + A[1];

            try {
                getfiguur(X, Y).move(getfiguur(X, Y).switch_move(move), player);
            }catch (Exception e){}

            update();
           return true;
        }
        return false;
    }

    public SpeelbordController getcontroller(Player player){
        for (var controller : ingeschrijven) {
            if (controller.getPlayer() ==  player){
                return controller;
            }
        }
        return null;
    }

    public int getZetten(){
        return speelbord[0][0].getzetten(max_X, max_Y);
    }


    public void update() {
        for (var item : ingeschrijven) {
            item.loid();
        }
    }


}
