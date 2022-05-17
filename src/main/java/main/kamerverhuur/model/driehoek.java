package main.kamerverhuur.model;

import javafx.scene.layout.Pane;
import main.kamerverhuur.Player;

public class driehoek implements figuren {
    public Boolean[] kant = {       false,
                                    false,
                                    false
                            };
    public boolean side = false;

    public Player ingekleurt = null;

    public int X;
    public int Y;

    public driehoek(int x, int y) {
        X = x;
        Y = y;
    }

    public driehoek(boolean side, int x, int y) {
        X = x;
        Y = y;
        this.side = side;
    }

    @Override
    public void teken(Pane pane, int factoor) {

    }

    @Override
    public Player gekleurt() {
        return ingekleurt;
    }

    @Override
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
                return new int[]{0, 1};
            default:
                return new int[]{1, -1};
        }
    }
        switch (move) {
            case 0:
                return new int[]{0, 1};
            case 1:
                return new int[]{0, -1};
            default:
                return new int[]{-1, 1};
        }
    }

    public int switch_move(int move){
        return move;
    }
}
