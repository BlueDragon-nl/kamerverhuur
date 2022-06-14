package main.kamerverhuur.model;

import javafx.geometry.Point2D;
import main.kamerverhuur.game;

public class Move {
    public Point2D point2D;
    public int move;

    public Player activeplayer;
    public game Game;

    public Move(Point2D point2D, int move) {
        this.point2D = point2D;
        this.move = move;
    }

    public int X(){
        return (int) point2D.getX();
    }

    public int Y(){
        return (int) point2D.getY();
    }

}
