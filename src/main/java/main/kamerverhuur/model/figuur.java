package main.kamerverhuur.model;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import main.kamerverhuur.Controllers.SpeelbordController;
import main.kamerverhuur.game;

public abstract class figuur {
    protected Player ingekleurt;
    protected Boolean[] kant;


    public int X;
    public int Y;
    public game Game;

    public figuur(int x, int y, game game) {
        X = x;
        Y = y;
        Game = game;
        ingekleurt = null;
    }
    
    public  Player gekleurt(){
        return ingekleurt;
    }

    public Polygon newPolygon(Point2D[] Points){
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



    public abstract void teken(Pane pane, int Xfactoor, int Yfactoor, SpeelbordController Controller);
    public abstract void move(int move, Player player);

    
    public abstract boolean solidmove(int move);
    public abstract boolean algedaan(int move);

    public abstract int[] getvakje2( int move);

    public abstract int switch_move(int move);

    public abstract int getzetten(int x, int y);

}
