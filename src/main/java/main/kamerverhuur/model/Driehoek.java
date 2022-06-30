package main.kamerverhuur.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.Game;

import java.util.ArrayList;

public class Driehoek extends Figuur {

    public boolean side = false;

    public Driehoek(int x, int y, Game game) {
        super(x,y,game);
        this.side = false;
        kant = new Boolean[]{false, false, false};
    }

    public Driehoek(boolean side, int x, int y, Game game) {
        super(x,y,game);
        this.side = side;
        kant = new Boolean[]{false, false, false};
    }



    protected Point2D[] point2DS1(int Xfactoor, int Yfactoor){

        int Y = (int) point.getY()/2;
        Double pointX = point.getX() * Xfactoor *2.0 + Xfactoor+5;
        Double pointY =  Y * Yfactoor *2.0 + Yfactoor+5;


        Point2D[] Points;
        if (!side){
            Points = new Point2D[]{
                    new Point2D(pointX - Xfactoor, pointY - Yfactoor),
                    new Point2D(pointX + Xfactoor, pointY - Yfactoor),
                    new Point2D(pointX + Xfactoor, pointY + Yfactoor)};
        }else {
            Points = new Point2D[]{
                    new Point2D(pointX + Xfactoor, pointY + Yfactoor),
                    new Point2D(pointX - Xfactoor, pointY + Yfactoor),
                    new Point2D(pointX - Xfactoor, pointY - Yfactoor)};

        }
        return Points;
    }




    @Override
    public boolean solidmove(int move) {
        if (0 <= move && move < kant.length){
            return true;
        }
        return false;
    }


    @Override
    public int[] getvakje2( int move) {
    if (!side) {
        switch (move) {
            case 0:
                return new int[]{0, -1};
            case 1:
                return new int[]{1, 1};
            default:
                return new int[]{0, 1};
        }
    }
        switch (move) {
            case 0:
                return new int[]{0, 1};
            case 1:
                return new int[]{-1, -1};
            default:
                return new int[]{0, -1};
        }
    }

    public int switch_move(int move){
        return move;
    }

    @Override
    public  int getzetten(int x, int y) {
        return x*(y/2)*3 + (x+(y/2));
    }
}
