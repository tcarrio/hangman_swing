//package me.carrio.hangman;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordGenerator {
	
	private static OnlineWordSearch webSearch;
	private ArrayList<String> localWords;
	private String randomWord;
	private final String fname = 
		"../data/word_list.ini";

	/**
	 * Constructor to intialize local word lists
	 */
	public WordGenerator(){
		webSearch = new OnlineWordSearch();
		localWords = new ArrayList<>();
		getLocalWords();
	}

	/**
	 * Returns a new word as a String
	 *
	 * Checks internet connectivity and uses the server if available,
	 * otherwise defaults to the local word cache
	 *
	 * @return 	String 	the next available word
	 */
	public String newWord(){
		if(webSearch.checkConnection()){
			randomWord = webSearch.newWord();
			return randomWord;
		} else {
			return randomLocalWord();
		}
	}

	/** 
	 * Randomly returns a word for the game
	 *
	 * Uses a pseudorandom number generator to return whichever word
	 * is stored in the localWords ArrayList at the index created 
	 *
	 * @return 	String 	the randomly selected local word
	 */
	private String randomLocalWord(){
		Random r = new Random();
		return localWords.get(r.nextInt(localWords.size()));
	}

	/**
	 * Reads the word file to load all the words into memory
	 */
	private void getLocalWords(){
		try{
			BufferedReader br = new BufferedReader(
				new FileReader(fname));
			String buffer;
			while((buffer=br.readLine())!=null){
				localWords.add(buffer);
			}
		} catch(IOException ioe){
			System.err.println("Could not load local words");
			ioe.printStackTrace();
		}
	}

}