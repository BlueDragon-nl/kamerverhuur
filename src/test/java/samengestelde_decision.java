import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import main.kamerverhuur.model.Move;
import main.kamerverhuur.model.Player;
import main.kamerverhuur.model.figuurs;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import main.kamerverhuur.game;

public class samengestelde_decision {
    @Test
    void Conditions(){
        game game = new game(0,0);
        game.newgame(figuurs.driehoek, 5, 5, true);

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
