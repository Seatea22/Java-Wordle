package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameModel;
import model.Observer;

import java.util.LinkedHashMap;

public class GameGUI extends Application implements Observer<GameModel, Object> {

    private static GameModel model;
    private static int rows;
    private static int columns;

    private String grey = "#707070";

    private Button resetButton;
    private GridPane buttonPane;
    private Label promptLabel;
    private LetterButton[][] buttons;

    private class LetterButton extends Button {
        private int row;
        private int col;

        public LetterButton(int row, int col) {
            this.row = row;
            this.col = col;
            this.setText("");
            this.setStyle("-fx-background-color: #" + grey);
        }

        public int[] getCoords() {
            return new int[]{row, col};
        }
    }

    @Override
    public void init() throws Exception {
        model.addObserver(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Wordle");
        BorderPane borderPane = new BorderPane();
        model = new GameModel();
        rows = model.getTries();
        columns = model.getAnswer().length();

        buttonPane = createButtons();
        VBox buttonBox = new VBox();
        buttonBox.getChildren().add(buttonPane);

        promptLabel = new Label("");
        VBox controlBox = new VBox();
        controlBox.getChildren().add(promptLabel);

        resetButton = new Button("Reset");
        resetButton.setPrefSize(75, 25);
        resetButton.setOnAction((event) -> {
            model.reset();
        });
        controlBox.getChildren().add(resetButton);

        borderPane.setRight(controlBox);
        borderPane.setCenter(buttonPane);

        Scene scene = new Scene(borderPane,600,600);
        update(model, null);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane createButtons() {
        GridPane gridPane = new GridPane();
        this.buttons = new LetterButton[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                LetterButton button = new LetterButton(row,col);

                gridPane.add(button,col,row);
                int[] coord = button.getCoords();
                buttons[coord[0]][coord[1]] = button;
            }
        }
        return gridPane;
    }

    private void displayBoard() {

    }

    @Override
    public void update(GameModel gameModel, Object o) {
        this.buttons = new LetterButton[rows][columns];

        displayBoard();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
