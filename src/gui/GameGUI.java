package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;
import model.Observer;

public class GameGUI extends Application implements Observer<GameModel, Object> {

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    @Override
    public void update(GameModel gameModel, Object o) {

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
