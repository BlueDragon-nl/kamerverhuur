package main.kamerverhuur.model;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import main.kamerverhuur.Controllers.SpeelbordController;

public class lijn {
    private Line line;
    private SpeelbordController Controller;

    public lijn(Line line, SpeelbordController controller) {
        this.line = line;
        Controller = controller;
    }

    public void pres(Move move, boolean used){
        line.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Controller.domove(move);
            }
        });

        if (used){
            line.setStyle("-fx-stroke: gray;");
        }
    }

    public Line getLine() {
        return line;
    }
}
