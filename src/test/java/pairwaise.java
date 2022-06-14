import main.kamerverhuur.game;
import main.kamerverhuur.model.figuurs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class pairwaise {
    @Test
    void pairwaisetesting(){
        game Game = new game();
        Game.newgame_test(figuurs.driehoek, 0, 5, true);
        Assertions.assertEquals(Game.speelbord.getZetten(), 0);

        Game.newgame_test(figuurs.driehoek, 5, 0, false);
        Assertions.assertEquals(Game.speelbord.getZetten(), 0);

        Game.newgame_test(figuurs.vierkant, 0, 0, false);
        Assertions.assertEquals(Game.speelbord.getZetten(), 0);

        Game.newgame_test(figuurs.vierkant, 5, 5, true);
        Assertions.assertEquals(Game.speelbord.getZetten(), 40);

        Game.newgame_test(figuurs.hexagon, 5, 5, false);
        Assertions.assertEquals(Game.speelbord.getZetten(), 160);


    }
}

