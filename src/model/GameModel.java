package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/** Class that holds all the functionality and data for a game to be played.
 * @author Caleb Talbott
 * @version 1.0
 * @since 1.0
 */
public class GameModel {
    public static final int TOTAL_MOVES = 5;
    public static final String FILEPATH = "src/input/sgb-words.txt";
    private static final File FILE = new File(FILEPATH);

    private int movesLeft;
    private int wordSize;
    private String answer;

    private ArrayList<String> words;

    private List<Observer<GameModel,Object>> observers;


    /**
     * Holds data including current turns left, the answer, and the word size as well.
     * Includes functions such as guess and reset.
     */
    public GameModel() {
        this.observers = new LinkedList<>();
        this.movesLeft = TOTAL_MOVES;

        try {
            words = parseWords();
            Collections.sort(words);
            answer = getWord();
            wordSize = answer.length();
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println(e);
        }
        //System.out.println(movesLeft + ", " + answer + ", " + wordSize);
        announce(null);
    }

    /**
     * Guesses a word, and returns a string.
     * @param word Takes in the guess as a string.
     * @return Returns a string with the guess format. Ex.) "X Y Y G X".
     * X: Means character is not in the word
     * Y: Character is in the word, wrong spot
     * G: Character is in the word, and right spot
     */
    public String guess(String word) {
        String res = "";
        for (int i = 0; i < answer.length(); i++) {
            String ch = "";
            ch += word.charAt(i);
            if (word.charAt(i) == answer.charAt(i)) {
                res += "G ";
            }
            else if (answer.contains(ch)) {
                res += "Y ";
            } else {
                res += "X ";
            }
        }
        this.movesLeft -= 1;
        announce(null);
        return res;
    }

    /**
     * Parses words from a file into an array seperated by line.
     * @return An ArrayList of various words.
     * @throws FileNotFoundException If file is not found
     */
    public static ArrayList<String> parseWords() throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(FILE);
            while (myReader.hasNextLine()) {
                res.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     * Gets a word from the "words" ArrayList.
     * @return A word from the "words" ArrayList.
     */
    private String getWord() {
        int index = 0;
        Random random = new Random();
        index = random.nextInt(0, words.size());
        return words.get(index);
    }

    //Getter methods
    public int getTries() {return movesLeft;}
    public String getAnswer() {return answer;}

    /**
     * Resets the model.
     */
    private void reset() {
        answer = getWord();
        movesLeft = TOTAL_MOVES;
        wordSize = answer.length();
    }

    /**
     * Announce to observers the model has changed;
     * @param arg A string (Can be null)
     */
    private void announce( String arg ) {
        for ( var obs : this.observers ) {
            obs.update( this, arg );
        }
    }

}
