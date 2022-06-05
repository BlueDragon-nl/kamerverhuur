package main.kamerverhuur.model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import main.kamerverhuur.game;

import java.util.ArrayList;

public class hexagon implements figuren {
    public Boolean[] kant = {       false,
            false,
            false,
            false,
            false,
            false,
            false,
            false
    };

    public int X;
    public int Y;
    public game Game;


    public hexagon(int x, int y, game Game) {
        X = x;
        Y = y;
        this.Game = Game;
    }
    public Player ingekleurt = null;


    @Override
    public void teken(Pane pane, int factoor, boolean yourturn) {
        int size = factoor;
        Double pointX = (X+1) * size *2.0;
        Double pointY = (Y+1) * size *2.0;
        int half = size/2;

        var Points = new Point2D[]{
                new Point2D(pointX - half, pointY - size),
                new Point2D(pointX + half, pointY - size),

                new Point2D(pointX + size, pointY - half),
                new Point2D(pointX + size, pointY + half),

                new Point2D(pointX + half, pointY + size),
                new Point2D(pointX - half, pointY + size),

                new Point2D(pointX - size, pointY + half),
                new Point2D(pointX - size, pointY - half)
        };
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < Points.length-1; i++ ) {
            lines.add(newline(Points[i], Points[i+1], i, yourturn));
        }
        lines.add(newline(Points[7], Points[0], 7, yourturn));
        Polygon hexagon = new Polygon();


        for (var point:Points) {
            hexagon.getPoints().add(point.getX());
            hexagon.getPoints().add(point.getY());
        }


        if (ingekleurt != null){
            hexagon.setFill(ingekleurt.color);
        }else {
            hexagon.setFill(Color.WHITE);
        }

        pane.getChildren().addAll(lines);
        pane.getChildren().add(hexagon);

    }



    public Line newline(Point2D Start, Point2D end, int position, boolean yourturn){
        Line line = new Line();

        line.setStartX(Start.getX());
        line.setStartY(Start.getY());

        line.setEndX(end.getX());
        line.setEndY(end.getY());

        line.setStrokeWidth(5.0);

        if (yourturn) {
            line.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Game.domove(X, Y, position, Game.getPlayers().getActivePlayer());
                }
            });
        }
        if (!kant[position]){
            line.setStyle("-fx-stroke: gray;");
        }

        return line;
    }

    @Override
    public Player gekleurt() {
        return ingekleurt;
    }

    @Override
    public void move(int move, Player player) {
        if (-1 < move && move < kant.length){
            game.setZetten();
            kant[move] = true;
        }
        boolean A = true;
        for (var side: kant) {
            if (!side){A = false;}
        }
        if (A){ingekleurt = player;}
    }
    @Override
    public boolean solidmove(int move) {
        if (0 <= move && move < kant.length){
            return true;
        }
        return false;
    }

    @Override
    public boolean algedaan(int move) {
        return !kant[move];
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

}
