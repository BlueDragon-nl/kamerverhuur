package main.kamerverhuur.model;

import javafx.scene.layout.Pane;
import main.kamerverhuur.Controllers.SpeelbordController;

public interface  figuren {
    public void teken(Pane pane, int factoor, SpeelbordController Controller);
    public Player gekleurt();
    public void move(int move, Player player);

    public boolean solidmove(int move);
    public boolean algedaan(int move);

    public int[] getvakje2( int move);

    public int switch_move(int move);

    public  int getzetten(int x, int y);

}
