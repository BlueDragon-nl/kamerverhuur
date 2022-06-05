package main.kamerverhuur.model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import main.kamerverhuur.game;

public class vierkant implements figuren {
    public Boolean[] kant = {       false,
            false,
            false,
            false
    };

    public int X;
    public int Y;


    public game Game;


    public vierkant(int x, int y, game Game) {
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


        Line[] lines = {
                newline(pointX - size, pointY - size, pointX + size, pointY - size, 0, yourturn),
                newline(pointX + size, pointY - size, pointX + size, pointY + size, 1, yourturn),
                newline(pointX + size, pointY + size, pointX - size, pointY + size, 2, yourturn),
                newline(pointX - size, pointY + size, pointX - size, pointY - size, 3, yourturn)
            };

        Polygon vierkant = new Polygon();


        vierkant.getPoints().setAll(pointX - size, pointY - size,
                pointX + size, pointY - size,
                pointX + size, pointY + size,
                pointX - size, pointY + size);

        if (ingekleurt != null){
            vierkant.setFill(ingekleurt.color);
        }else {
            vierkant.setFill(Color.WHITE);
        }

        pane.getChildren().addAll(lines);
        pane.getChildren().add(vierkant);

        }



    public Line newline(Double StartX, Double StartY, Double endX, Double endY, int position, boolean yourturn){
        Line line = new Line();

        line.setStartX(StartX);
        line.setStartY(StartY);

        line.setEndX(endX);
        line.setEndY(endY);

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
}