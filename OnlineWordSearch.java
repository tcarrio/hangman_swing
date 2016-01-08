import java.io.*;
import java.net.*;

public class OnlineWordSearch {
	
	private static final int MAX_LETTERS = 15;
	private static URL url;
	//private static HttpUrlConnection connection;

	public static String randomize(){
		randomize(MAX_LETTERS);

	}

	public static String randomize(int length){
		if(length > MAX_LETTERS)
			length = MAX_LETTERS;

		url = new URL(String.format("http://randomword.setgetgo.com/get.php?len=%d",length));

		try {
			InputStream is = url.openStream();
			BufferedReader br = new BufferedReader(is);
			StringBuilder response = new StringBuilder();
			String line;
			while((line=br.readLine())!= null){
				response.append(line);
				response.append('\r');
			}
			System.out.println(response.toString());
			return response.toString().substring(0,'\r');
		} catch(IOException ioe){
			ioe.printStackTrace();
		} catch(Exception e){
			System.err.println(e.toString());
		} finally {
			if(br != null){
				br.close();
			}
		}
	}
}