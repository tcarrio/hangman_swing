import java.util.*;
import java.io.*;

public class WordGenerator {
	
	private static OnlineWordSearch webSearch;
	private ArrayList<String> localWords;
	private String randomWord;
	private final String fname = 
		"../source/python/data/word_list.ini";

	public WordGenerator(){
		webSearch = new OnlineWordSearch();
		localWords = new ArrayList<>();
		getLocalWords();
	}

	public String randomize(){
		if(webSearch.checkConnection()){
			randomWord = webSearch.randomize();
			return randomWord;
		} else {
			return randomLocalWord();
		}
	}

	private String randomLocalWord(){
		Random r = new Random();
		return localWords.get(r.nextInt(localWords.size()));
	}

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