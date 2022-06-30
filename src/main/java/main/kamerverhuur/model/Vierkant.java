package main.kamerverhuur.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.Game;

import java.util.ArrayList;

public class Vierkant extends Figuur {
    public Vierkant(int x, int y, Game game) {
        super(x,y,game);
        kant = new Boolean[]{false, false, false, false};
    }

    @Override
    protected Point2D[] point2DS1(int Xfactoor, int Yfactoor) {
        Double pointX = point.getX() * Xfactoor *2.0 + Xfactoor + 5;
        Double pointY = point.getY() * Yfactoor *2.0 + Yfactoor + 5;

        return  new Point2D[]{
                new Point2D(pointX - Xfactoor, pointY - Yfactoor),
                new Point2D(pointX + Xfactoor, pointY - Yfactoor),

                new Point2D(pointX + Xfactoor, pointY + Yfactoor),
                new Point2D(pointX - Xfactoor, pointY + Yfactoor),
        };
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
        switch (move) {
            case 0:
                return new int[]{0, -1};
            case 1:
                return new int[]{1, 0};
            case 2:
                return new int[]{0, 1};
            default:
                return new int[]{-1, 0};
        }
    }

    @Override
    public int switch_move(int move) {
        switch (move) {
            case 0:
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            default:
                return 1;
        }
    }

    @Override
    public int getzetten(int x, int y) {
        return x*y*2 + (x+y);
    }
}