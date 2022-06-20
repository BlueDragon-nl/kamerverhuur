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

public class driehoek extends figuur {

    public boolean side = false;

    public driehoek(int x, int y, game Game) {
        super(x,y,Game);
        this.side = false;
        kant = new Boolean[]{false, false, false};
    }

    public driehoek(boolean side, int x, int y, game Game) {
        super(x,y,Game);
        this.side = side;
        kant = new Boolean[]{false, false, false};
    }

    @Override
    public void teken(Pane pane, int Xfactoor, int Yfactoor, SpeelbordController Controller) {
        Point2D[] Points = point2DS(Xfactoor, Yfactoor);

        ArrayList<Line> lines = makelines(Points, Controller);
        Polygon driehoek = newPolygon(Points);

        pane.getChildren().addAll(lines);
        pane.getChildren().add(driehoek);
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
    protected ArrayList<Line> makelines2(Point2D[] Points, SpeelbordController Controller){
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < Points.length-1; i++ ) {
            lijn lijn = new lijn(newline(Points[i], Points[i+1]), Controller);
            lijn.pres(new Move(new Point2D(point.getX(),point.getY()), i), !kant[i]);
            lines.add(lijn.getLine());
        }

        lijn lijn = new lijn(newline(Points[2], Points[0]), Controller);
        lijn.pres(new Move(new Point2D(point.getX(),point.getY()), 2), !kant[2]);
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
