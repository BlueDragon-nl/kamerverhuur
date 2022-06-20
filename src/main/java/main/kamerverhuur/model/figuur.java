package main.kamerverhuur.model;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.game;

import java.util.ArrayList;

public abstract class figuur {
    protected Player ingekleurt;
    protected Boolean[] kant;


    public Point2D point;
    public game Game;

    public figuur(int x, int y, game game) {
        point = new Point2D(x, y);
        Game = game;
        ingekleurt = null;
    }
    
    public  Player gekleurt(){
        return ingekleurt;
    }

    public Polygon newPolygon3(Point2D[] Points){
        Polygon vierkant = new Polygon();
        for (var point:Points) {
            vierkant.getPoints().add(point.getX());
            vierkant.getPoints().add(point.getY());
        }

        if (ingekleurt != null){
            vierkant.setFill(ingekleurt.color);
        }else {
            vierkant.setFill(Color.WHITE);
        }
        return vierkant;
    }

    protected Line newline(Point2D Start, Point2D end){
        Line line = new Line();

        line.setStartX(Start.getX());
        line.setStartY(Start.getY());

        line.setEndX(end.getX());
        line.setEndY(end.getY());

        line.setStrokeWidth(5.0);
        return line;
    }

    protected abstract Point2D[] point2DS1(int Xfactoor, int Yfactoor);
    protected abstract ArrayList<Line> makelines2(Point2D[] Points, SpeelbordController Controller);

    public void teken(Pane pane, int Xfactoor, int Yfactoor, SpeelbordController Controller){
        Point2D[] Points = point2DS1(Xfactoor, Yfactoor);

        ArrayList<Line> lines = makelines2(Points, Controller);
        Polygon polygon = newPolygon3(Points);

        additem4(pane, lines, polygon);
    }

    public void additem4(Pane pane, ArrayList<Line> lines, Polygon polygon){
        pane.getChildren().addAll(lines);
        pane.getChildren().add(polygon);
    }

    public void move(int move, Player player) {
        if (-1 < move && move < kant.length){
            kant[move] = true;
        }
        boolean A = true;
        for (var side: kant) {
            if (!side){A = false;}
        }
        if (A){ingekleurt = player;}
    }

    
    public abstract boolean solidmove(int move);

    public boolean algedaan(int move) {
        return !kant[move];
    }

    public abstract int[] getvakje2( int move);

    public abstract int switch_move(int move);

    public abstract int getzetten(int x, int y);

}
