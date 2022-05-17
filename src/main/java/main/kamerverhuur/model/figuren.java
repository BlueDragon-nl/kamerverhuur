package main.kamerverhuur.model;

import javafx.scene.layout.Pane;
import main.kamerverhuur.Player;

public interface  figuren {
    public void teken(Pane pane, int factoor);
    public Player gekleurt();
    public void move(int move, Player player);

    public boolean solidmove(int move);
    public boolean algedaan(int move);

    public int[] getvakje2( int move);

    public int switch_move(int move);
}
