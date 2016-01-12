/**
 * Created by tom on 1/10/16.
 */
import javax.swing.JLabel;
import java.util.ArrayList;

public class HangmanWord extends JLabel{

    private String secretWord;
    private String displayWord;

    /**
     * Default constructor for HangmanWord label for the game
     * @param word
     */
    public HangmanWord(String word){
        super(word.replace("[A-Za-z]","X"));
        secretWord=word;
        displayWord=super.getText();
    }

    public String getSecretWord(){
    	return secretWord;
    }

    public boolean revealLetter(char letter){
    	return revealLetter(letter,0, new ArrayList<Integer>());
    }

    private boolean revealLetter(char letter, int ind,
    	ArrayList<Integer> list){

    	ind=secretWord.indexOf(letter,ind);
    	if(ind!=-1){
    		list.add(ind);
    		revealLetter(letter,ind,list);
    	} else {
    		if(list.size()>0){
    			char[] swArr = displayWord.toCharArray();
    			list.stream()
    				.forEach(l -> swArr[l]=letter);
				displayWord=new String(swArr);
				super.setText(displayWord);
				return true;
    		} else
    			return false;
    	}
    	return false;
    }
}
