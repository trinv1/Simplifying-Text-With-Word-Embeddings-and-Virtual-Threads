package ie.atu.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * The TextFileProcessor class is responsible for loading and processing words from a text file.
 * It stores the words in a ConcurrentHashMap for thread-safe access.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */

public class TextFileProcessor {

	//Encapsulated words in list
    private final List<String> textWords = new ArrayList<>();

    /**
     * Loading in the text file using virtual threads.
     * 
     * Runtime: 0(n)
     * Reasoning: Each line in file is read once: 0(n)
     * 			  For each line a task is submitted to thread pool: 0(1)
     * 		      Each line is processed and has a set structure: 0(1) 
     * 
     * Since these operations are performed for all lines in file, the total time required grows directly 
     * with the number of lines, making overall runtime O(n).
     *
     * @param textFilePath The path to the text file.
     * @throws Exception If there is an error reading the file.
     */
    public void load(String textFilePath) throws Exception {
        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(textFilePath))
                .forEach(line -> pool.execute(() -> process(line)));
        }
    }
    
    /**
     * Processes single line from google file.
     * 
     * Runtime: 0(1)
     * Reasoning: Trimming line to get rid of whitespacing: 0(1)
     * 			  Adding word to list: 0(1) on average
     *  
     * Number of operations done is constant regardless
     * of input size.
     * 			 
     *
     * @param line The line to process.
     */
    private void process(String line) {
            line = line.trim(); //Trimming line
            if (!line.isEmpty()) { 
                textWords.add(line); //Add processed line to list 
            }
    }
    
    /**
     * Retrieves the list containing words.
     * 
     * Runtime: 0(1) - Simply returns words, accessing field does not depend on size of map
     *
     * @return a list of processed lines from file
     */
    public List<String> getWords() {
        return textWords;
    }
}
