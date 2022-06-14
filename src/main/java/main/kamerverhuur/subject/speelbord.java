package main.kamerverhuur.subject;

import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.model.Player;
import main.kamerverhuur.model.figuur;
import main.kamerverhuur.model.Move;

public class speelbord extends subject<SpeelbordController> {

    private figuur[][] speelbord;
    private int max_X, max_Y;

    public speelbord(int max_X, int max_Y){
        this.max_X = max_X;
        this.max_Y = max_Y;
    }



    public void setSpeelbord(figuur[][] speelbord) {
        this.speelbord = speelbord;
        if (speelbord[0][0].getClass().toString().equals("class main.kamerverhuur.model.driehoek")){
            max_Y *= 2;
        }
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


    public figuur getfiguur(int X, int y){
        return speelbord[X][y];
    }

    public Boolean domove(Move move){
        if (MoveCheck(move.X(), move.Y(), move.move)){
            speelbord[move.X()][move.Y()].move(move.move, move.activeplayer);

            var A = getfiguur(move.X(), move.Y()).getvakje2(move.move);

            int X = move.X() + A[0];
            int Y = move.Y() + A[1];

            try {
                getfiguur(X, Y).move(getfiguur(X, Y).switch_move(move.move), move.activeplayer);
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
