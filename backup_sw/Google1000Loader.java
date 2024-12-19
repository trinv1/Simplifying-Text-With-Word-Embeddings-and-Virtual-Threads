package ie.atu.sw;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

/**
 * The Google1000Loader class is responsible for loading Googles 1000 most commonly used words from a file.
 * It stores the words in a ConcurrentHashMap for thread-safe access.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */
public class Google1000Loader {
	
	//Encapsulated words in concurrent hash map
    private Map<String, Boolean> googleWords = new ConcurrentHashMap<>();

	/**
	 * Method used to load in google1000Words file
	 * Running time: 0(n) 
	 * - Reads each line from the file once (O(n)).
	 * - Replaces white spaces with no space
	 * - Adds the word to the map
	 * - Overall, the method runs in linear time O(n), where n is the number of lines in the file.
	 * 
	 * @param googleWordsFilePath The path to the Google 1000 words file.
	 * @throws Exception If there is an error reading the file.
	 */
    public void load(String googleWordsFilePath) throws Exception{ 
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(googleWordsFilePath)))) {
			
			String line;
	        while ((line = br.readLine()) != null) { // Looping through each line in the google file
	            line = line.replaceAll("\\s", ""); //Replacing white spaces with no space
	            
	            if (!line.isEmpty()) { //Making sure line isnt empty
	                googleWords.put(line, true); //Adding the word to map 
	            }
	        }
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the embeddings file. " + e.getMessage());		
		}
	}
    
    /**
     * Retrieves the map containing google words.
     *
     * @return a ConcurrentHashMap of google words
     */
    public Map<String, Boolean> getGoogleWords() {
        return googleWords;
    }

}

