package main.kamerverhuur.model;

import javafx.scene.layout.Pane;
import main.kamerverhuur.Player;

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

    public hexagon(int x, int y) {
        X = x;
        Y = y;
    }

    public Player ingekleurt = null;


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
        return 7-move;
    }

}
