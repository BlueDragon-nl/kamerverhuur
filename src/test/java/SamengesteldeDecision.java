import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import main.kamerverhuur.Game;

import main.kamerverhuur.subject.builder.SpeelbordVierkant;
import main.kamerverhuur.model.Move;
import main.kamerverhuur.model.Player;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class SamengesteldeDecision {
    @Test
    void Conditions(){
        Game game = new Game();
        var bord = new SpeelbordVierkant();
        game.newGame(bord.newSpeelbord (5, 5, game, false));

        Player red =  new Player("red", Color.RED);
        game.getPlayers().Inschrijven(red);

        Player blue =  new Player("blue", Color.BLUE);
        game.getPlayers().Inschrijven(blue);

        //hier mee start ik de game en de logica wie er begint rood begint altijd
        game.startgame();

        //ik doe een move dan kan ik straks checken of deze move algedaan is
        Move domove = new Move(new Point2D(1,1), 1);
        game.domove(domove);

        Move newmove = new Move(new Point2D(1,1), 2);

        Move foutmove = new Move(new Point2D(1,1), 5);

       Assertions.assertFalse(game.CanDoMove(newmove, red));
       Assertions.assertFalse(game.CanDoMove(domove, blue));
       Assertions.assertFalse(game.CanDoMove(foutmove, blue));

        Assertions.assertTrue(game.CanDoMove(newmove, blue));
    }
}
