//package me.carrio.hangman;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.ArrayList;

public class OnlineWordSearch {
	
	private static final int MAX_LETTERS = 15;
	private final int LIST_ADD_LIMIT = 20;
	private int index = 0;
	private static URL url;
	private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    private ArrayList<String> wordCollection;

    /**
     * Creates an ArrayList and fills it with LIST_ADD_LIMIT words
     */
    public OnlineWordSearch(){
    	wordCollection = new ArrayList<>(LIST_ADD_LIMIT);
    	addToList();
    }

    /**
     * If internet connection available, add another set of words to list
     */
    private void addToList(){
    	if(checkConnection()){
    		for(int i=0;i<LIST_ADD_LIMIT;i++){
    			wordCollection.add(randomize());
    		}
    	}
    }

    /**
     * Generates a random length int between 7 and 15. 
     *
     * @return 	int 	a random length suitable for Hangman
     */
    private int generateLength(){
    	Random r = new Random();
    	return r.nextInt(8)+8;
    }

    /**
     * Returns the next word for the game
     *
     * Iterates to the next index of the local list, if out of words,
     * calls addToList to check for connection and add more words. 
     */
    public String newWord(){
    	if(index>=wordCollection.size())
    		addToList();
    	return wordCollection.get(index++);
    }

    /**
     * Request and returns a String from the server of random length [7-15]
     *
     * Use generateLength and randomize(int length) to request and return a 
     * String from the server of a random length. 
     * @return 	String 	the random String returned from the server
     */
	private String randomize(){
		return randomize(generateLength());
	}

	/**
	 * Requests a word from the server of length 'length'
	 *
	 * Uses the user provided length, given it is of suitable size, to retrieve
	 * a word from the server.
	 *
	 * @return 	String 	the random String returned from the server
	 * @param 	int 	the length to be used to request the word [7-15]
	 */
	private String randomize(int length){
		if(length > MAX_LETTERS || length < 6)
			length = generateLength();

		try {
			url = new URL(String.format(
				"http://randomword.setgetgo.com/get.php?len=%d",length));
			is = url.openStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			StringBuilder response = new StringBuilder();
			String line;
			while((line=br.readLine())!= null){
				response.append(line);
				response.append('\r');
			}
			return response.toString().toUpperCase().substring(0,length);
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(Exception e){
			System.err.println(e.toString());
		} finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    System.err.println("Attempted to close BufferedReader br when"
                            + " already collected");
                }
            }
        }
        return "";
	}

	/**
	 *	Requests a webpage to determine if internet access is available
	 *
	 * @return 	boolean 	the status of internet connectivity
	 */
	public boolean checkConnection(){
		try{
			URL test = new URL("http://www.google.com");
			InputStream isTest = test.openStream();
			return true;
		} catch(Exception e){
			return false;
		}
	}
}