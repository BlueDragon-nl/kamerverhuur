package main.kamerverhuur.model;

import javafx.scene.paint.Color;
import main.kamerverhuur.Game;
import main.kamerverhuur.subject.Speelbords.Speelbord;


public class Player {
    public String name;
    public Color color;

    public boolean isturn;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        isturn = false;
    }

    public int score(Speelbord speelbord){
        int count = 0;

        for (int x=0; x< speelbord.getMax_X(); x++){
            for (int y=0; y < speelbord.getMax_Y(); y++){
               if (speelbord.getfiguur(x, y).gekleurt() == this){
                   count++;
               }
            }
        }
        return count;
    }

    public void update(Game game){
       var controller = game.speelbord.getcontroller(this);
        if (controller != null){controller.yourturn(isturn);}
    }
}
