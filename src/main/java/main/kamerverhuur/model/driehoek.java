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

public class driehoek implements figuren {
    public Boolean[] kant = {       false,
                                    false,
                                    false
                            };
    public boolean side = false;

    public Player ingekleurt = null;

    public int X;
    public int Y;
    public game Game;

    public driehoek(int x, int y, game Game) {
        X = x;
        Y = y;
        this.Game = Game;
        this.side = false;
    }

    public driehoek(boolean side, int x, int y, game Game) {
        X = x;
        Y = y;
        this.Game = Game;
        this.side = side;
    }

    @Override
    public void teken(Pane pane, int factoor, boolean yourturn) {
        int size = factoor/2;
        Double pointX = (X+1) * size *2.0;
        Double pointY = (Y/2) * size *2.0+ 50;

        Point2D[] Points;
        if (!side){

            Points = new Point2D[]{
                    new Point2D(pointX - size, pointY - size),
                    new Point2D(pointX + size, pointY - size),
                    new Point2D(pointX + size, pointY + size)};
        }else {
            Points = new Point2D[]{
                    new Point2D(pointX + size, pointY + size),
                    new Point2D(pointX - size, pointY + size),
                    new Point2D(pointX - size, pointY - size)};

        };

        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < Points.length-1; i++ ) {
            lines.add(newline(Points[i], Points[i+1], i, yourturn));
        }
        lines.add(newline(Points[2], Points[0], 2, yourturn));

        Polygon driehoek = new Polygon();

        for (var point:Points) {
            driehoek.getPoints().add(point.getX());
            driehoek.getPoints().add(point.getY());
        }


        if (ingekleurt != null){
            driehoek.setFill(ingekleurt.color);
        }else {
            driehoek.setFill(Color.WHITE);
        }

        pane.getChildren().addAll(lines);
        pane.getChildren().add(driehoek);

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
    if (!side) {
        switch (move) {
            case 0:
                return new int[]{0, -1};
            case 1:
                return new int[]{1, 1};
            default:
                return new int[]{1, -1};
        }
    }
        switch (move) {
            case 0:
                return new int[]{0, 1};
            case 1:
                return new int[]{-1, -1};
            default:
                return new int[]{-1, 1};
        }
    }

    public int switch_move(int move){
        return move;
    }
}
