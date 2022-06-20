import main.kamerverhuur.game;
import main.kamerverhuur.model.figuurs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class pairwaise {
    @Test
    void pairwaisetesting(){

        Assertions.assertEquals(-1, zetten(figuurs.driehoek, 0, 5, true));
        Assertions.assertEquals(-1, zetten(figuurs.driehoek, 5, 0, false));

        Assertions.assertEquals(-1, zetten(figuurs.vierkant, 0, 0, false));
        Assertions.assertEquals(40, zetten(figuurs.vierkant, 5, 5, true));

        Assertions.assertEquals(-1, zetten(figuurs.hexagon, 0, 0, true));
        Assertions.assertEquals(128, zetten(figuurs.hexagon, 5, 5, false));

    }
    private int zetten(figuurs figuur, int X , int Y,  Boolean Sides){
        game game = new game(X,Y);
        game.newgame(figuur, Sides);
        return game.getLEFTTurn();
    }

}

