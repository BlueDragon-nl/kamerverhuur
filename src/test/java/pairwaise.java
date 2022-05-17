import main.kamerverhuur.Speelbord;
import main.kamerverhuur.game;
import main.kamerverhuur.model.figuurs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class pairwaise {
    @Test
    void pairwaisetesting(){
        game Game = new game();
        Game.newgame_test(figuurs.driehoek, 0, 5, true);
        Assertions.assertEquals(game.getZetten(), 0);

        Game.newgame_test(figuurs.driehoek, 5, 0, true);
        Assertions.assertEquals(game.getZetten(), 0);


    }
}

