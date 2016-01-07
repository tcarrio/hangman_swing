import java.io.*;
import java.net.*;

public class OnlineWordSearch {
	
	private static final int MAX_LETTERS = 15;
	private static URL url;
	private static HttpUrlConnection connection;

	public static String randomize(){
		randomize(MAX_LETTERS);

	}

	public static String randomize(int length){
		if(length > MAX_LETTERS)
			length = MAX_LETTERS;

		url = String.format("http://randomword.setgetgo.com/get.php?len=%d",length);

		InputStream is = url.openStream();

		try {
			BufferedReader br = new BufferedReader(is);
			StringBuilder response = new StringBuilder();
			String line;
			while((line=br.readLine())!= null){
				response.append(line);
				response.append('\r');
			}
			br.close();
			System.out.println(response.toString());
			return response.toString().substring(0,'\r');
		}

	}
}