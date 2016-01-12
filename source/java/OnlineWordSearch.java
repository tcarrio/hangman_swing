import java.io.*;
import java.net.*;

public class OnlineWordSearch {
	
	private static final int MAX_LETTERS = 15;
	private static URL url;
	private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
	//private static HttpUrlConnection connection;

	public String randomize(){
		return randomize(MAX_LETTERS);
	}

	public String randomize(int length){
		if(length > MAX_LETTERS)
			length = MAX_LETTERS;

		try {
			url = new URL(String.format("http://randomword.setgetgo.com/get.php?len=%d",length));
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
			return response.toString().substring(0,'\r');
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