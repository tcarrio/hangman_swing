package me.carrio.hangman;

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

    public OnlineWordSearch(){
    	wordCollection = new ArrayList<>(LIST_ADD_LIMIT);
    	addToList();
    }

    private void addToList(){
    	if(checkConnection()){
    		for(int i=0;i<LIST_ADD_LIMIT;i++){
    			wordCollection.add(randomize());
    		}
    	}
    }

    private int generateLength(){
    	Random r = new Random();
    	return r.nextInt(8)+7;
    }

    public String newWord(){
    	if(index>=wordCollection.size())
    		addToList();
    	return wordCollection.get(index++);
    }

	private String randomize(){
		return randomize(generateLength());
	}

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
			System.out.println(response.toString());
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

	public boolean checkConnection(){
		return true;
	}
}