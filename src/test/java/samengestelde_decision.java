import main.kamerverhuur.model.figuurs;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import main.kamerverhuur.game;

public class samengestelde_decision {
    @Test
    void Conditions(){
        game game = new game();
        game.newgame(figuurs.vierkant, 5, 4, true);

       //Assertions.assertFalse(game.MoveCheck(0,0,0));
        //Assertions.assertFalse(game.MoveCheck(2,2,7));
        //Assertions.assertTrue(game.MoveCheck(2,2,0));
    }
}
