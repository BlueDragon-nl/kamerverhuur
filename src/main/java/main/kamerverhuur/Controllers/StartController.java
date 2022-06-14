package main.kamerverhuur.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.kamerverhuur.Main;
import main.kamerverhuur.model.Player;
import main.kamerverhuur.game;
import main.kamerverhuur.model.figuurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;

public class StartController implements Initializable {

    @FXML
    private TextField X, Y;

    @FXML
    private ChoiceBox<figuurs> figuursbox;

    @FXML
    private ChoiceBox<Integer> aantalPlayers;

    @FXML
    private Button newgame;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private CheckBox sides;

    @FXML
    public void newgame(ActionEvent event) throws IOException {
        int getX =  parseInt((X.getText()));
        int getY =  parseInt(Y.getText());


        game game = new game();
        game.newgame(figuursbox.getValue(), getX, getY, sides.isSelected());

        var color = new Color[]{Color.RED, Color.BLUE, Color.GREEN, Color.BROWN, Color.ORANGE, Color.PINK};

        for (int I = 1; I < aantalPlayers.getValue().intValue()+1; I++) {
            Player player = new Player(""+I ,color[I-1]);

            SpeelbordController speelbordController = Screen(player, game);
            speelbordController.set(game, player);
            speelbordController.loid();
        }

        game.startgame();
    }

    public SpeelbordController Screen(Player player, game game) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/main/kamerverhuur/SpeelbordView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("player: " + player.name);
        stage.setScene(scene);
        stage.show();


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                game.speelbord.Uitschrijven((SpeelbordController) fxmlLoader.getController());
                game.getPlayers().Uitschrijven(player);
            }
        });

        return (SpeelbordController) fxmlLoader.getController();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        figuursbox.getItems().addAll(figuurs.values());
        aantalPlayers.getItems().add(2);
        aantalPlayers.getItems().add(4);
        aantalPlayers.getItems().add(6);
    }
}
