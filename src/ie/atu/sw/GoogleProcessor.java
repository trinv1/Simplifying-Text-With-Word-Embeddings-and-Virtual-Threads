package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * The GoogleProcessor class is responsible for loading Googles 1000 most commonly used words from a file.
 * It stores the words in a ConcurrentHashMap for thread-safe access.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */

public class GoogleProcessor {

	//Encapsulated words in concurrent hash map
    private Map<String, Boolean> googleWords = new ConcurrentHashMap<>();

    /**
     * Loading the google file using virtual threads.
     * 
     * Runtime: 0(n), n is number of lines in file    * 
     * Reasoning: Each line in file is read once: 0(n)
     * 			  For each line a task is submitted to thread pool: 0(1)
     * 		      Each line is processed and has a set structure: 0(1) 
     * 
     * Since these operations are performed for all lines in file, the total time required grows directly 
     * with the number of lines, making overall runtime O(n).
     *
     * @param embeddingsFilePath The path to the embeddings file.
     * @throws Exception If there is an error reading the file.
     */
    public void load(String googleFilePath) throws Exception {
        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(googleFilePath))
                .forEach(line -> pool.execute(() -> process(line)));
        }
    }
    
    /**
     * Processes single line from google file.
     * 
     * Runtime: 0(1), each line only have 1 word.
     * Reasoning: Trimming line to get rid of whitespacing: 0(1)
     * 			  Adding word to map: 0(1) on average
     *  
     * Number of operations done is constant regardless
     * of input size.
     * 			 
     *
     * @param line The line to process.
     */
    private void process(String line) {
            String word = line.trim(); //Trimming line
            if (!word.isEmpty()) { 
                googleWords.put(word, true); //Adding the word to map 
            }
    }
    
    /**
     * Retrieves the map containing google words.
     * 
     * Runtime: 0(1) - Simply returns google words, accessing field does not depend on size of map
     *
     * @return a ConcurrentHashMap of google words
     */
    public ConcurrentHashMap<String, Boolean> getGoogleWords() {
        return (ConcurrentHashMap<String, Boolean>) googleWords;
    }
}
