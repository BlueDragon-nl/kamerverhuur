import main.kamerverhuur.Game;
import main.kamerverhuur.Factory;
import main.kamerverhuur.subject.builder.SpeelbordDriehoek;
import main.kamerverhuur.subject.builder.SpeelbordHexagon;
import main.kamerverhuur.subject.builder.SpeelbordVierkant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class pairwaise {
    @Test
    void pairwaisetesting(){
        Assertions.assertEquals(-1, zetten(new SpeelbordDriehoek(), 0, 5, true));
        Assertions.assertEquals(-1, zetten(new SpeelbordDriehoek(), 5, 0, false));

        Assertions.assertEquals(-1, zetten(new SpeelbordVierkant(), 0, 0, false));
        Assertions.assertEquals(40, zetten(new SpeelbordVierkant(), 5, 5, true));

        Assertions.assertEquals(-1, zetten(new SpeelbordHexagon(), 0, 0, true));
        Assertions.assertEquals(128, zetten(new SpeelbordHexagon(), 5, 5, false));

    }
    private int zetten(Factory figuur, int X , int Y, Boolean Sides){
        Game game = new Game();
        game.newGame(figuur.newSpeelbord(X, Y, game, Sides));
        return game.getLEFTTurn();
    }

}

