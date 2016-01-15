//package me.carrio.hangman;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.Arrays;

public class HangmanWord extends JLabel{

    private String secretWord;
    private String displayWord;
    private String defaultDisplay;
    private WordGenerator wordGen;
    private int corrected,wrong,totalTries;
    private boolean playing;

    /**
     * Default constructor for HangmanWord label for the game
     */
    public HangmanWord(){
        super();
        wordGen = new WordGenerator();
        defaultDisplay = generateDisplayWord();
        super.setText(defaultDisplay);
        super.setVisible(true);
        super.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Sets up the local variables for a new session. 
     * 
     * Makes calls to get a new secret word, runs logic to determine
     * the length of the secret word and sets the display text and stores
     * a local dummy variable to perform logic on and set text.
     */ 
    public void newGame(){
        playing=true;
        corrected=0;
        wrong=0;
        totalTries=10;
    	secretWord = wordGen.newWord();
        displayWord = generateDisplayWord(secretWord);
        super.setText(displayWord);
    }

    /**
     * Resets variables to end the current game session
     *
     * Resets the values related to running the game. This does not manually change
     * the display, but alters encapsulated values relevant to the game logic. 
     */
    public void endGame(){
        playing=false;
    	secretWord="";
    	displayWord=defaultDisplay;
    }

    /**
     * Generates a 15 character length X-filled String
     *
     * Creates a String to use for the display of the Hangman word at the start of
     * the game to be manipulated as the game goes on.
     *
     * @return  String  the String containing only X's of length 15
     */
    private String generateDisplayWord( ){
    	char[] tempArr = new char[15];
        Arrays.fill(tempArr,'X');
        return new String(tempArr);
    }

    /**
     * Generates an X-filled String of length the same as the provided word
     *
     * Creates a String to use in place of the provided word, likely the secret
     * word that is to be used for this session of Hangman. This will be revealed
     * to the user as they make guesses.
     *
     * @param   String  the String to use to generated an equal-length X-filled String
     * @return  String  the String containing only X's whose length equals that of word
     */
    private String generateDisplayWord(String word){
    	char[] tempArr = new char[word.length()];
        Arrays.fill(tempArr,'X');
        return new String(tempArr);
    }

    /**
     * Returns the secret word stored in the object
     *
     * @return 	String 	The local secret word
     */
    public String getSecretWord(){
    	return secretWord;
    }

    /**
     * Publicly accessible method to search for the given char
     *
     * Entry method for calls to start searching for and revealing
     * the given letter in the secret word, will be reflected in the
     * display text when completed. 
	 *
	 * @return 	int 	whether any characters were found
	 * @param 	char 		the letter to search for in the secret word
     */
    public int revealLetter(Character letter){
        int code = 1;
        char[] swArr = secretWord.toCharArray();
        char[] dwArr = displayWord.toCharArray();
        for(int i=0;i<swArr.length;i++){
            if(letter==swArr[i]){
                dwArr[i]=letter;
                code = 0;
                corrected++;
            }
        }
        displayWord = new String(dwArr);
        super.setText(displayWord);
        wrong+=code;
        return code;
    }

    /**
     * Retrieve local int totalTries
     *
     * @return  int     total allowed tries
     */
    public int getTotalTries(){
        return totalTries;
    }

    /**
     * Retrieve local int correct
     *
     * @return  int     number of corrected letters
     */
    public int getCorrect(){
        return corrected;
    }

    /**
     * Retrieve local int wrong
     *
     * @return  int     number of wrong guesses this game
     */
    public int getWrong(){
        return wrong;
    }

    /**
     * Provides a code for the game status
     *
     * @return  int     code representing the status of the game
     */
    public int isGameOver(){
        if(playing)
            return (wrong!=totalTries)
                ? corrected/secretWord.length()
                : -1; 
        else
            return -2;
    }

    /**
     * Generates the String filled with number of tries for GUI display
     *
     * Retrieves the number of tries made by the user and displays it 
     * on the application as the game progresses. 
     *
     * @return  String  a String containing the number of tries the users had
     */
    public String gameStatusString(){
        return String.format("Tries:%2d",wrong);
    }

}
