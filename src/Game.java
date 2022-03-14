import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.util.*;

/** Represents an employee.
 * @author Caleb Talbott
 * @version 1.0
 * @since 1.0
 * This was the first file I used to create my Wordle game in Java. This was before I decided to split it up into a
 * model and interface.
 */

public class Game {
    private static String filepath = "C:/Users/caleb/IdeaProjects/WordleWordAid/src/sgb-words.txt";
    private static File file = new File(filepath);

    private static String answer;
    private static int tries;

    public static void main(String[] args) throws IOException {
        //answer = args[0].toLowerCase();

        ArrayList<String> words = parseWords();
        Collections.sort(words);
        int index = 0;
        Random random = new Random();
        index = random.nextInt(0, words.size());
        answer = words.get(index);

        tries = Integer.parseInt(args[0]);
        Scanner scanner = new Scanner(System.in);
        String emptyString = "";
        for (int i = 0; i < answer.length(); i++) {
            emptyString += "_ ";
        }
        System.out.println(emptyString);
        System.out.println("Tries left: " + tries);
        String input = scanner.nextLine();

        while(tries > 0) {
            if (input.length() != answer.length()) System.out.println("Not the same length.");
            else {
                if (isSolution(input)) {
                    System.out.println("You won! The word is: " + answer);
                    break;
                } else {
                    String res = "";
                    for (int i = 0; i < answer.length(); i++) {
                        String ch = "";
                        ch += input.charAt(i);
                        if (input.charAt(i) == answer.charAt(i)) {
                            res += "G ";
                        }
                        else if (answer.contains(ch)) {
                            res += "Y ";
                        } else {
                            res += "X ";
                        }
                    }
                    System.out.println(res);
                    System.out.println("Tries left: " + tries);
                }
            }
            input = scanner.nextLine();
        }
        System.out.println("The answer is "+ answer);
    }

    public static boolean isSolution(String input) {
        return Objects.equals(input.toUpperCase(), answer.toUpperCase());
    }

    public static ArrayList<String> parseWords() throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                res.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return res;
    }

}
