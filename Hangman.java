import javax.swing.*;
import java.util.*;

public class Hangman
{
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JMenuBar mainMenu;
	private JMenu fileMenu, helpMenu;
	private JMenuItem quitItem, aboutItem;
	private String welcomeStr, startStr, stopStr;
	private JButton actionButton;
	private ArrayList<JButton> alphaButtons;
	private char[] alphabet;

	public Hangman(){
		// Initialize all basic components of app
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		mainMenu = new JMenuBar();
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About");
		welcomeStr = "Welcome to Hangman";
		startStr = "Start Game";
		stopStr = "Stop Game";
		alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L',
			'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		alphaButtons = new ArrayList<JButton>();
		for(char c: alphabet){
			alphaButtons.add(new JButton(c));
		}



	}

	private boolean startGame(){
		//
	}

	private boolean stopGame(){
		// reset all counters
	}

	private int onLetterClicked(char c){
		// returns 1 if letter found
		// returns 0 if bad guess
		// returns -1 if letter searched already
	}

	private static void main(String[] args){
		new Hangman();
	}
}