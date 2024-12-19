package ie.atu.sw;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.*;

/**
 * The EmbeddingLoader class is responsible for loading word embeddings from a file.
 * It stores the embeddings in a ConcurrentHashMap for thread-safe access.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */
public class EmbeddingLoader {
	
	//Encapsulated embeddings in concurrent hash map
    private Map<String, double[]> embeddings = new ConcurrentHashMap<>();
    
	
	/**
	 * Method used to load in embeddings file
	 * Running time: 0(n) 
	 * - Reads each line from the file once (O(n)).
	 * - Splits each line and processes 50 features (constant time).
	 * - Adds the word and features to the map (constant time).
	 * - Overall, the method runs in linear time O(n), where n is the number of lines in the file.
	 */
    public void load(String embeddingsFilePath) throws Exception{ 
		try (var br = new BufferedReader(new InputStreamReader(new FileInputStream(embeddingsFilePath)))) {

			String line;
			while ((line = br.readLine()) != null) { //Looping through each line in the embeddings file
				
		String[] wordAndFeatures = line.split(",\\s*");//Splitting the line into word and its matching values
		                
				if (wordAndFeatures.length == 51) { //Ensuring each line has 1 word followed by 50 features
                    String word = wordAndFeatures[0].toLowerCase();;
                    double[] feature = new double[50];
                    
                    for (int i = 1; i < wordAndFeatures.length; i++) { //Looping over split parts of line from index 1
                        feature[i - 1] = Double.parseDouble(wordAndFeatures[i]); //Converting each part from string to double and storing in feature array
                    }
                    
                    embeddings.put(word, feature); //Adding to the map
                    
                }
		
			}
		} catch (Exception e) {
			throw new Exception("[ERROR] Encountered a problem reading the embeddings file. " + e.getMessage());		
		}
	}
    
    /**
     * Retrieves the map containing word embeddings.
     *
     * @return a ConcurrentHashMap of word embeddings
     */
    public Map<String, double[]> getEmbeddings() {
        return embeddings;
    }

}
