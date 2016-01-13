/**
 * Created by tom on 1/10/16.
 */
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.Arrays;

public class HangmanWord extends JLabel{

    private String secretWord;
    private String displayWord;
    private String defaultDisplay;
    private WordGenerator wordGen;
    private int corrected;

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
        corrected=0;
    	secretWord = wordGen.randomize();
        displayWord = generateDisplayWord(secretWord);
        super.setText(displayWord);
    }

    /**
     *
     */
    public void endGame(){
    	secretWord="";
    	displayWord=defaultDisplay;
    	super.setText(displayWord);
    }

    /**
     *
     */
    private String generateDisplayWord( ){
    	char[] tempArr = new char[15];
        Arrays.fill(tempArr,'X');
        return new String(tempArr);
    }

    /**
     *
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
        int code = 0;
        char[] swArr = secretWord.toCharArray();
        char[] dwArr = displayWord.toCharArray();
        for(int i=0;i<swArr.length;i++){
            if(letter==swArr[i]){
                dwArr[i]=letter;
                code = 1;
            }
        }
        displayWord = new String(dwArr);
        super.setText(displayWord);
        return code;
    }

    /**
     * Publicly accessible method to search for the given Character
     *
     * Recursive method used to search for and reveal the given
     * letter in the secret word. Collects all indices of instances of the character 
     * in a list which will be used to replace all characters in the String and set 
     * the text to the new output, reflecting the success/failure of the user 
     *
     * @return 	int 	whether any characters were found
	 * @param 	Character 		the letter to search for in the secret word
	 * @param 	int 		index to search from (starts at 0)
	 * @param 	ArrayList	list to be filled with integer indices
     */
    private int revealLetter(Character letter, int ind,
    	ArrayList<Integer> list){

    	ind=secretWord.indexOf(letter,ind);
    	if(ind!=-1 && ind<secretWord.length()){
    		list.add(ind);
            System.out.println("Second run:");
    		revealLetter(letter,ind,list);
    	} else {
    		
    	}
    	return 0;
    }
}
