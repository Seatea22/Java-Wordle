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

    public static void main(String[] args) {
        while(true) {
            model = new GameModel();
            Scanner scanner = new Scanner(System.in);
            String emptyString = "";
            for (int i = 0; i < model.getAnswer().length(); i++) {
                emptyString += "_ ";
            }
            System.out.println(emptyString);
            System.out.println("Tries left: " + model.getTries());

            while (model.getTries() > 0) {
                String input = scanner.nextLine();
                String response = "";
                String expected = "";
                for (int i = 0; i < model.getAnswer().length(); i++) {
                    expected += "G ";
                }

                if (input.length() != model.getAnswer().length()) System.out.println("Not the same length.");
                else response = model.guess(input);

                if (expected.equals(response)) {
                    System.out.println("You won!");
                    break;
                } else if (!expected.equals(response) && model.getTries() > 0) {
                    System.out.println("Try Again!\n" + response);
                    System.out.println("Tries left: " + model.getTries());
                } else if (!expected.equals(response) && model.getTries() <= 0) {
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
}
