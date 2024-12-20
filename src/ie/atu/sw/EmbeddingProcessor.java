package ie.atu.sw;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * The EmbeddingProcessor class is responsible for loading word embedding from a file using virtual threading.
 * It stores the embedding in a ConcurrentHashMap for thread-safe access.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */
public class EmbeddingProcessor {

	//Encapsulated embeddings in concurrent hash map
    private Map<String, double[]> embeddings = new ConcurrentHashMap<>();
    	
    /**
     * Loading the embeddings file using virtual threads.
     * 
     * Runtime: 0(n) 
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
    public void load(String embeddingsFilePath) throws Exception {
        try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(embeddingsFilePath))
                .forEach(line -> pool.execute(() -> process(line)));
        }
    }
    
    /**
     * Processes a single line, getting the word and its embedding.
     * 
     * Runtime: 0(1), number of parts in the line is constant  * 
     * Reasoning: The number of operations done is contant 
     * 			  regardless of the size of the input file.
     *
     * @param line The line to process.
     */
    private void process(String line) {
        String[] parts = line.split(",\\s*");
        if (parts.length == 51) {
            String word = parts[0].toLowerCase();
            double[] vector = new double[50];
            for (int i = 1; i < parts.length; i++) {
                vector[i - 1] = Double.parseDouble(parts[i]);
            }
            embeddings.put(word, vector); //Adding to map
        }
    }

    /**
     * Retrieves the map of embeddings.    * 
     * Runtime: 0(1) - Simply returns embeddings, accessing field does not depend on size of map
     *
     * @return A ConcurrentHashMap containing word embeddings.
     */
    public ConcurrentHashMap<String, double[]> getEmbeddings() {
        return (ConcurrentHashMap<String, double[]>) embeddings;
    }
}
