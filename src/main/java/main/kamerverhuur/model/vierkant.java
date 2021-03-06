package main.kamerverhuur.model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.game;

import java.util.ArrayList;

public class vierkant extends figuur {
    public vierkant(int x, int y, game Game) {
        super(x,y,Game);
        kant = new Boolean[]{false, false, false, false};
    }

    @Override
    protected Point2D[] point2DS1(int Xfactoor, int Yfactoor) {
        Double pointX = point.getX() * Xfactoor *2.0 + Xfactoor+5;
        Double pointY = point.getY() * Yfactoor *2.0 + Yfactoor+5;

        return  new Point2D[]{
                new Point2D(pointX - Xfactoor, pointY - Yfactoor),
                new Point2D(pointX + Xfactoor, pointY - Yfactoor),

                new Point2D(pointX + Xfactoor, pointY + Yfactoor),
                new Point2D(pointX - Xfactoor, pointY + Yfactoor),
        };
    }

    @Override
    protected ArrayList<Line> makelines2(Point2D[] Points, SpeelbordController Controller){
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < Points.length-1; i++ ) {
            lijn lijn = new lijn(newline(Points[i], Points[i+1]), Controller);
            lijn.pres(new Move(new Point2D(point.getX(),point.getY()), i), !kant[i]);
            lines.add(lijn.getLine());
        }
        lijn lijn = new lijn(newline(Points[3], Points[0]), Controller);
        lijn.pres(new Move(new Point2D(point.getX(),point.getY()), 3), !kant[3]);
        lines.add(lijn.getLine());
        return lines;
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