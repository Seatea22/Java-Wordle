package game;

import model.GameModel;

import java.util.Scanner;

/**
 * PTUI for the game that can be ran in text-form. Uses functionality from GameModel.
 * @author Caleb Talbott
 * @version 1.0
 * @since 1.0
 */

public class GamePTUI {
    private static GameModel model; //Game model
    private static String[][] board;

    public static void main(String[] args) {
        while(true) {
            model = new GameModel();
            board = new String[model.getTries()][model.getAnswer().length()];
            Scanner scanner = new Scanner(System.in);
            displayBoard();
            System.out.println("Tries left: " + model.getTries());

            while (model.getTries() > 0) {
                String input = scanner.nextLine();

                if (input.length() != model.getAnswer().length()) System.out.println("Not the same length.");
                else {
                    model.guess(input);

                    String response = model.getGuess();
                    String[] responseSplit = response.split("\\s+");

                    for (int i = 0; i < responseSplit.length; i++) {
                        board[model.TOTAL_MOVES - model.getTries()][i] = responseSplit[i];
                    }
                    displayBoard();
                    model.setTries(model.getTries() - 1);
                }

                if (model.isSolution()) {
                    System.out.println("You won!");
                    break;
                } else if (!model.isSolution() && model.getTries() > 0) {
                    System.out.println("Try Again!");
                    System.out.println("Tries left: " + model.getTries());
                } else if (!model.isSolution() && model.getTries() <= 0) {
                    System.out.println("You lost!");
                }
            }
            System.out.println("The answer was " + model.getAnswer() + ".");
            System.out.println("Try Again? (Y|N)");
            String retry = scanner.nextLine().toUpperCase();
            if (retry.contains("Y")) {
                System.out.println("Resetting...");
            } else break;
        }
    }

    private static void displayBoard() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    res.append("_");
                } else {
                    res.append(board[i][j]);
                }
                res.append(" ");
            }
            res.append("\n");
        }
        System.out.println(res);
    }
}
