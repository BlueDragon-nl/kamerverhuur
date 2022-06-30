package main.kamerverhuur.subject.builder;

import main.kamerverhuur.Factory;
import main.kamerverhuur.Game;
import main.kamerverhuur.model.Vierkant;
import main.kamerverhuur.subject.Speelbords.Speelbord;

public class SpeelbordVierkant implements Factory<Vierkant> {

    private int X;
    private int Y;


    private Vierkant[][] speelbord;

    public Speelbord newSpeelbord(int x, int y, Game game, boolean Sides) {
        if (x == 0 || y == 0){return new Speelbord(x, y, null, 0);}
        X = x;
        Y = y;
        int sides = 0;

        speelbord = speelveld(game);
        if (Sides){setborder();
            sides = (X + Y) *2;
        }
        return new Speelbord(X, Y, speelbord, sides);
    }


    public String getName() {
        return "Vierkant";
    }

    @Override
    public Vierkant[][] speelveld(Game game){
        Vierkant[][] speelveld = new Vierkant[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                speelveld[x][y] = new Vierkant(x, y, game);
            }
        }
        return speelveld;
    }

    @Override
    public void setborder(){
        for (int x=0; x< X ; x++){
            speelbord[x][0].move(0,null);
            speelbord[x][Y-1].move(2,null);

        }
        for (int y=0; y<Y; y++){
            speelbord[0][y].move(3,null);
            speelbord[X-1][y].move(1,null);

        }
    }

}
