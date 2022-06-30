package main.kamerverhuur.subject.builder;

import main.kamerverhuur.Factory;
import main.kamerverhuur.Game;
import main.kamerverhuur.model.Hexagon;
import main.kamerverhuur.subject.Speelbords.Speelbord;

public class SpeelbordHexagon implements Factory<Hexagon> {

    private int X;
    private int Y;

    private Hexagon[][] speelbord;

    public String getName() {
        return "Hexagon";
    }

    public Speelbord newSpeelbord(int x, int y, Game game, boolean Sides) {
        if (x == 0 || y == 0){return new Speelbord(x, y, null, 0);}
        X = x;
        Y = y;
        int sides = 0;
        speelbord = speelveld(game);
        if (Sides){setborder();
            sides = ((X + Y) * 3 -2) * 2;
        }
        return new Speelbord(X, Y, speelbord, sides);
    }

    public Hexagon[][] speelveld(Game game){
        Hexagon[][] speelveld = new Hexagon[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                speelveld[x][y] = new Hexagon(x, y, game);
            }
        }
        return speelveld;
    }
    public void setborder(){
        for (int x=0; x< X ; x++){
            speelbord[x][0].move(1,null);
            speelbord[x][0].move(0,null);
            speelbord[x][0].move(7,null);

            speelbord[x][Y-1].move(3,null);
            speelbord[x][Y-1].move(4,null);
            speelbord[x][Y-1].move(5,null);

        }
        for (int y = 0; y<Y; y++){
            speelbord[0][y].move(7,null);
            speelbord[0][y].move(6,null);
            speelbord[0][y].move(5,null);

            speelbord[X-1][y].move(1,null);
            speelbord[X-1][y].move(2,null);
            speelbord[X-1][y].move(3,null);
        }
    }
}
