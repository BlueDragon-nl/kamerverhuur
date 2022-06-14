package main.kamerverhuur.model;

import main.kamerverhuur.game;

public class Move {
    public int X;
    public int Y;
    public int move;

    public Player activeplayer;
    public game Game;

    public Move(int x, int y, int move) {
        X = x;
        Y = y;
        this.move = move;
    }
}
