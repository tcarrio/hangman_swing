import java.net.HttpUrlConnection;

public class OnlineWordSearch {
	
	private static final int MAX_LETTERS = 15;
	private static URL url;
	private static HttpUrlConnection connection;

	public static char randomize(){
		randomize(MAX_LETTERS);

	}

	public static char randomize(int length){
		if(length > MAX_LETTERS)
			length = MAX_LETTERS;

		try{
			url = new URL("http://randomword.setgetgo.com/get.php");
			connection = (HttpUrlConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("len",Integer.toString(MAX_LETTERS));


		}


	}
}