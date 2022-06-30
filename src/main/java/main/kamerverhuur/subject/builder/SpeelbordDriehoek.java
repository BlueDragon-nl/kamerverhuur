package main.kamerverhuur.subject.builder;

import main.kamerverhuur.Factory;
import main.kamerverhuur.Game;
import main.kamerverhuur.model.Driehoek;
import main.kamerverhuur.subject.Speelbords.Speelbord;

public class SpeelbordDriehoek implements Factory<Driehoek> {
    private int X;
    private int Y;

    private Driehoek[][] speelbord;

    public String getName() {
        return "Driehoek";
    }

    @Override
    public Speelbord newSpeelbord(int x, int y, Game game, boolean Sides) {
        if (x == 0 || y == 0){return new Speelbord(x, y, null, 0);}
        X = x;
        Y = y * 2;
        int sides = 0;

        speelbord = speelveld(game);
        if (Sides){setborder();
            sides = (X + Y) *2;
        }

        return new Speelbord(X, Y, speelbord, sides);
    }

    @Override
    public Driehoek[][] speelveld(Game game){
        Driehoek[][] speelveld = new Driehoek[X][Y];
        for (int x=0; x< X; x++){
            for (int y=0; y < Y; y++){
                if (y % 2 == 0){
                    speelveld[x][y] = new Driehoek(x, y, game);
                }else {
                    speelveld[x][y] = new Driehoek(true, x, y, game);
                }
            }
        }
        return speelveld;
    }

    @Override
    public void setborder(){

        for (int x=0; x< X ;x++){
            speelbord[x][0].move(0,null);
            speelbord[x][Y-1].move(0,null);

        }
        for (int y=1; y<Y; y++){
            if (y % 2 == 0){
                speelbord[0][y-1].move(1,null);
            }else {
                speelbord[X-1][y-1].move(1,null);
            }

        }
        speelbord[0][Y-1].move(1,null);


    }

}
