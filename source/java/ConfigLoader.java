import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ConfigLoader{
	
	private static String configFileName
		= "config.ini";
	private static HashMap<String,Object> configMap 
		= new HashMap<>();
	private static boolean loaded = false;
	private static ArrayList<String> configKeys
		= new ArrayList<>(Arrays.asList(
			"verbose",
			"config_file",
			"use_network",
			"user_name",
			"max_tries"));
    private static ArrayList<String> configList;
    private static BufferedReader reader;

	/**
	 * Loads the configuration using the default filename
	 * 
	 * Creates an ArrayList of lines from the config.ini file, which is then
	 * processed using logic to fill the HashMap with correct settings. Uses
	 * the global HashMap without returning any variables. Once the program 
	 * has loaded the config, a boolean is switched and the program will not 
	 * reload the configuration again until restarted. 
	 */
	private static void loadConfig(){
		
		try {
			configList = new ArrayList<>();
			reader = new BufferedReader(
				new FileReader(configFileName));
			String buffer;
			while((buffer=reader.readLine())!= null){
					configList.add(buffer);
			}
		} catch(IOException ioe){
			ioe.printStackTrace();
		} finally {
			try {
				if(reader!=null){
					reader.close();
				}
			} catch(IOException ioe){
				; // reader is closed
			}
		}

		configList.stream()
			.filter(c ->
				(c.length()>2
					&& c.indexOf(":")>0
					&& c.charAt(0)!='#'))
			.forEach(c -> {
				String[] k_v = c.split(":");
				if(configKeys.contains(k_v[0]))
					configMap.put(k_v[0],k_v[1]);
			});
	}

	/**
	 * Public method to return HashMap of configuration
	 *
	 * Checks whether the configuration has been loaded previously, calls
	 * loadConfig() if not, or returns the existing configuration map
	 *
	 * @return 	HashMap	a map containing configuration pairs
	 */
	public static HashMap<String,Object> getConfig(){
		if(!loaded)
			loadConfig();
		return configMap;
	}
}