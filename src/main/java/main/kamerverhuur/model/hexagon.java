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

public class hexagon extends figuur {

    public hexagon(int x, int y, game Game) {
        super(x,y,Game);
        kant = new Boolean[]{false, false, false, false, false, false, false, false};
    }

    @Override
    public Polygon newPolygon3(Point2D[] Points){
        Polygon vierkant = new Polygon();
        for (var point:Points) {
            vierkant.getPoints().add(point.getX());
            vierkant.getPoints().add(point.getY());
        }

        if (ingekleurt != null){
            vierkant.setFill(ingekleurt.color);
        }else {
            vierkant.setFill(Color.BLUE);
        }
        return vierkant;
    }

    @Override
    public ArrayList<Line> makelines2(Point2D[] Points, SpeelbordController Controller){
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < Points.length-1; i++ ) {
            lijn lijn = new lijn(newline(Points[i], Points[i+1]), Controller);
            lijn.pres(new Move(new Point2D(point.getX(),point.getY()), i), !kant[i]);
            lines.add(lijn.getLine());
        }
        lijn lijn = new lijn((newline(Points[7], Points[0])), Controller);
        lijn.pres(new Move(new Point2D(point.getX(),point.getY()), 7), !kant[7]);
        lines.add(lijn.getLine());

        return lines;
    }


    @Override
    protected Point2D[] point2DS1(int Xfactoor, int Yfactoor){
        Double pointX = point.getX() * Xfactoor *2.0 + Xfactoor+5;
        Double pointY = point.getY() * Yfactoor *2.0 + Yfactoor+5;
        int Xhalf = Xfactoor/2;
        int Yhalf = Yfactoor/2;

        return  new Point2D[]{
                new Point2D(pointX - Xhalf, pointY - Yfactoor),
                new Point2D(pointX + Xhalf, pointY - Yfactoor),

                new Point2D(pointX + Xfactoor, pointY - Yhalf),
                new Point2D(pointX + Xfactoor, pointY + Yhalf),

                new Point2D(pointX + Xhalf, pointY + Yfactoor),
                new Point2D(pointX - Xhalf, pointY + Yfactoor),

                new Point2D(pointX - Xfactoor, pointY + Yhalf),
                new Point2D(pointX - Xfactoor, pointY - Yhalf)
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
        switch (move){
            case 0: return new int[]{0, -1};
            case 1: return new int[]{1, -1};
            case 2: return new int[]{1, 0};
            case 3: return new int[]{1, 1};
            case 4: return new int[]{0, 1};
            case 5: return new int[]{-1, 1};
            case 6: return new int[]{-1, 0};
            default: return new int[]{-1, -1};
        }
    }
    public int switch_move(int move){
        switch (move) {
            case 0:
                return 4;
            case 1:
                return 5;
            case 2:
                return 6;
            case 3:
                return 7;
            case 4:
                return 0;
            case 5:
                return 1;
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    @Override
    public int getzetten(int x, int y){
       return x* y* 6 + (x+y);
    }

}
