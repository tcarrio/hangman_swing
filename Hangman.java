import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Hangman
{
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JMenuBar mainMenu;
	private JMenu fileMenu, helpMenu;
	private JMenuItem quitItem, aboutItem;
	private String welcomeStr, fileStr, helpStr, quitStr, aboutStr, startStr, 
		stopStr, disKeyStr, disKeyTitle;
	private JButton actionButton;
	private ArrayList<JButton> alphaButtons;
	private HashMap<Character,Boolean> prevSearch;
	private char[] alphabet;
	private JLabel welcomeLabel, wordLabel;
	private WordGenerator wordGen;

	/**
	 * Sole constructor. Sets all global variables within Hangman class.
	 */
	public Hangman(){
		// Initialize all basic components of app

		// Fill all strings for the application
		welcomeStr = "Welcome to Hangman";
		startStr = "Start Game";
		stopStr = "Stop Game";
		fileStr = "File";
		helpStr = "Help";
		quitStr = "Quit";
		aboutStr = "About";
		disKeyStr = "Keys disabled while game is stopped";
		disKeyTitle = "Error: Game not started!";

		// Generate character array (0:A, 1:B, etc.)
		alphabet = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L',
			'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

		// Initialize all GUI elements
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		mainMenu = new JMenuBar();
		fileMenu = new JMenu(fileStr);
		helpMenu = new JMenu(helpStr);
		quitItem = new JMenuItem(quitStr);
		aboutItem = new JMenuItem(aboutStr);
		
		alphaButtons = new ArrayList<JButton>();
		for(char c: alphabet){
			alphaButtons.add(new JButton(c));
		}

		welcomeLabel = new JLabel(welcomeStr);

	}

    /**       
     * Sets all fields to start a new game.          
     *        
     * Returns a boolean once all the operations to start the game have been 
     * completed. Attempts to set all action listeners for the buttons, sets 
     * the label for the unknown word to the correct length and filled with 
     * X's, and returns true if successful. Else, false.       
     *       
     * @return  whether the game was succesfully started       
     */          
	private boolean startGame(){
		
	}

	/**      
     * Returns a boolean once all the operations to stop the game have been 
     * completed.
	 * Attempts to remove all action listeners for the buttons, sets the label
	 * for the unknown word to the maximum length (16) and fills with X's, and
	 * returns true if successful. Else, false. 
	 * 
	 * @return 	whether the game was succesfully stopped 
	 */     
	private boolean stopGame(){
		
	}

	/**
	 * Performs the logic for searched characters. This will determine the
	 * char input by the user, check against previously searched characters,
	 * then check against the unknown word.
	 * If the char is found in the word, those chars will be revealed to the
	 * user. Else, one attempt will be added and the total attempts displayed
	 * to the user.
	 * 
	 * @param 	letter 	the letter input by user to be searched
	 * @return 	int 	status code for letter search
	 */
	private int onLetterClicked(char letter){
		// returns 0 if bad guess
		// returns 1 if letter found
		// returns 2 if letter searched already
	}

	/**
	 * Adds or removes all button listeners according to the boolean argument.
	 * If true, will add an ActionListener to all JButtons in the alphabet
	 * field. Else, all ActionListeners will be removed from all JButtons in 
	 * the alphabet field. 
	 * 
	 * @param 	start 	whether to add/remove listeners
	 */
	private void setButtonListeners(boolean start){
		if(start){
			alphaButtons.parallelStream()
				.forEach(b -> {
					b.removeActionListener();
					b.addActionListener( new ActionListener(){
						public void actionPerformed(ActionEvent ev){
							onLetterClicked(Character.parseChar(b.getText().strip()));
						}
					});
				});
		} else {
			alphaButtons.parallelStream()
				.forEach(b -> {
					b.removeActionListener();
					b.addActionListener( new ActionListener(){
						public void actionPerformed(ActionEvent ev){
							
						}
					});
				});
		}
	}

	/** 
	 * Creates a new instance of the Hangman class, starting the GUI
	 * application. 
	 *
	 * @param 	args 	arguments passed in to the application at runtime
	 */
	private static void main(String[] args){
		new Hangman();
	}

}