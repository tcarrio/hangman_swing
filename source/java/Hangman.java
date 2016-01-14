import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

public class Hangman
{
	private JFrame mainFrame;
	private JPanel innerPanel,topPanel,bottPanel,buttonPanel;
	private JComponent mainPanel;
	private JPanel[] subButtonPanels;
	private GridBagConstraints c;
	private JMenuBar mainMenu;
	private JMenu fileMenu, helpMenu;
	private JMenuItem quitItem, aboutItem;
	private final String WELCOME_STR, FILE_STR, HELP_STR, QUIT_STR, ABOUT_STR, 
		START_STR, STOP_STR, DIS_KEY_MSG, DIS_KEY_TITLE, TITLE_STR;
	private JButton actionButton;
	private ArrayList<JButton> alphaButtons;
	private HashMap<Character,Boolean> prevSearch;
	private char[] alphabet;
	private JLabel welcomeLabel, wordLabel;
	private WordGenerator wordGen;
	private final int X_SIZE=720, Y_SIZE=480;
	private ActionListener startedListener, stoppedListener, gameListener;
	private Action startedAction, stoppedAction;
	private Font titleFont,hangmanFont;
	private HangmanWord hangmanWord;
	private int bDimension;
	private ArrayList<Character> searchedChars;
	private boolean gameStatus;

	/**
	 * Sole constructor. Sets all global variables within Hangman class.
	 */
	public Hangman(){
		// Initialize all basic components of app

		// Fill all strings for the application
		TITLE_STR = "Hangman! - By Tom Carrio";
		WELCOME_STR = "Welcome to Hangman";
		START_STR = "Start Game";
		STOP_STR = "Stop Game";
		FILE_STR = "File";
		HELP_STR = "Help";
		QUIT_STR = "Quit";
		ABOUT_STR = "About";
		DIS_KEY_MSG = "Keys disabled while game is stopped";
		DIS_KEY_TITLE = "Error: Game not started!";

		// Generate character array (0:A, 1:B, etc.)
        ArrayList<Character> alphabet = new ArrayList<>(
                Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L',
                        'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
        );

		// Initialize all GUI elements
		mainFrame = new JFrame();
		mainPanel = (JComponent)mainFrame.getContentPane();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		innerPanel = new JPanel(new BorderLayout());
		buttonPanel = new JPanel(new GridLayout(3,1));
		subButtonPanels = new JPanel[3];
		for(int i=0;i<subButtonPanels.length;i++){
			subButtonPanels[i]=new JPanel(new GridBagLayout());
		}
		bottPanel = new JPanel(new BorderLayout());
		topPanel = new JPanel(new GridBagLayout());
		mainMenu = new JMenuBar();
		fileMenu = new JMenu(FILE_STR);
		helpMenu = new JMenu(HELP_STR);
		quitItem = new JMenuItem(QUIT_STR);
		aboutItem = new JMenuItem(ABOUT_STR);
		hangmanWord = new HangmanWord();
		bDimension = 128;
		searchedChars = new ArrayList<>(26);
		actionButton = new JButton(START_STR);

		// Constraints setup
		c = new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;

		// Generate JButtons array using alphabet
		alphaButtons = new ArrayList<JButton>();
        alphabet.stream()
                .forEach(c ->
                    alphaButtons.add(new JButton(
                    Character.toString(c))));

		titleFont = new Font("Arial",Font.BOLD,24);
		welcomeLabel = new JLabel(WELCOME_STR);
		welcomeLabel.setFont(titleFont);
		
		hangmanFont = new Font("Arial",Font.BOLD,48);
		hangmanWord.setFont(hangmanFont);
		
		startedListener = 
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					//System.out.println("Button clicked");
					onLetterClicked(
						((JButton)ev.getSource())
						.getText().charAt(0));
				}
			};
		stoppedListener = 
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					System.err.println(
						"Game has not been started!");
					//new JToast(null,null);
				}
			};
		gameListener = 
			new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					if(gameStatus)
						stopGame();
					else
						startGame();
				}
			};
		startedAction = 
			new AbstractAction(){
				public void actionPerformed(ActionEvent ev){
					//System.out.println("Button clicked");
					onLetterClicked(
						((JButton)ev.getSource())
						.getText().charAt(0));
				}
			};
		stoppedAction = 
			new AbstractAction(){
				public void actionPerformed(ActionEvent ev){
					System.err.println(
						"Game has not been started!");
					//new JToast(null,null);
				}
			};

		actionButton.addActionListener(gameListener);

		// setup GUI elements
		mainFrame.setTitle(TITLE_STR);
		mainFrame.setSize(X_SIZE,Y_SIZE);
		Dimension winDim = getWindowLocation();
		mainFrame.setLocation((int)winDim.getWidth(),(int)winDim.getHeight());
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Menu setup
		fileMenu.add(quitItem);
		helpMenu.add(aboutItem);
		mainMenu.add(fileMenu);
		mainMenu.add(helpMenu);
		mainFrame.setJMenuBar(mainMenu);
		innerPanel.add(hangmanWord,BorderLayout.PAGE_START);

		// JButton panel setup
		for(int i=0; i<alphaButtons.size();i++){
			c.gridx=i%10;
			subButtonPanels[i/10].add(alphaButtons.get(i),c);
		}
		for(JPanel j : subButtonPanels){
			buttonPanel.add(j);
		}
		innerPanel.add(buttonPanel,BorderLayout.CENTER);

		// Start/Stop game panel setup
		bottPanel.add(actionButton,BorderLayout.LINE_START);

		// Title setup
		topPanel.add(welcomeLabel);
		mainFrame.add(topPanel,BorderLayout.PAGE_START);
		mainFrame.add(innerPanel,BorderLayout.CENTER);
		mainFrame.add(bottPanel,BorderLayout.PAGE_END);

		// display to user
		mainFrame.setVisible(true);

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
		System.out.println("Game started");
		hangmanWord.newGame();
		setButtonListeners(true);
		actionButton.setText(STOP_STR);
		gameStatus=true;
		return true;
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
		System.out.println("Game stopped");
		setButtonListeners(false);
		actionButton.setText(START_STR);
		gameStatus=false;
		return true;
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
		int code;
		if(searchedChars.contains(letter)){
			code = 2;
		} else {
			code = hangmanWord.revealLetter(letter);
		}
		System.out.print(code);
		return code;

		// return (searchedChars.contained(letter))
		// 		? 2
		// 		: hangmanWord.revealLetter(letter)
	}

	/**
	 * Calculates the location to put the application window upon startup.
	 *
	 * Uses the screen resolution of the system and calculates where to place
	 * the JFrame on the screen to moreover center the application for the 
	 * user. 
	 *
	 * @return 	Dimension 	contains the (x,y) coordinates for the application
	 */
	private Dimension getWindowLocation(){
		Dimension sysDim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)(sysDim.getWidth()-X_SIZE)/2;
		int y = (int)(sysDim.getHeight()-Y_SIZE)/2;
		return new Dimension(x,y);
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
					for(ActionListener a : b.getActionListeners()){
						b.removeActionListener(a);
					}
					b.addActionListener(startedListener);
					b.getInputMap().put(
						KeyStroke.getKeyStroke(b.getText().charAt(0)),"pressed");
					b.getActionMap().put(
						"pressed",startedAction);
				});
		} else {
			alphaButtons.parallelStream()
				.forEach(b -> {
					for(ActionListener a : b.getActionListeners()){
						b.removeActionListener(a);
					}
					b.addActionListener(stoppedListener);
					b.getInputMap().put(
						KeyStroke.getKeyStroke(b.getText().charAt(0)),"pressed");
					b.getActionMap().put("pressed",
						stoppedAction);
				});
		}
	}

	/** 
	 * Creates a new instance of the Hangman class, starting the GUI
	 * application. 
	 *
	 * @param 	args 	arguments passed in to the application at runtime
	 */
	public static void main(String[] args){
		new Hangman();
	}

}