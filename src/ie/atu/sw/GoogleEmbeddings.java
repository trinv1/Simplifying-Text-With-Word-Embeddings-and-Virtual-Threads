package ie.atu.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The googleEmbeddings class is responsible for retrieving the embeddings from the WordEmbeddings map
 * that match the words in the Google 1000 map.
 * It stores the google embeddings in a new thread safe Concurrent Hash Map.
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 19
 */

public class GoogleEmbeddings {
	
	//Declaring objects
	GoogleProcessor googleProcessor = new GoogleProcessor();
	EmbeddingProcessor embeddingProcessor = new EmbeddingProcessor();
	
	 //Retrieving existing Concurrent Hash Maps
	 ConcurrentHashMap<String, Boolean> googleWords = googleProcessor.getGoogleWords();
	 ConcurrentHashMap<String, double[]> embeddings = embeddingProcessor.getEmbeddings();
	 
	 //Creating new concurrent hash map to store google embeddings
	 ConcurrentHashMap<String, double[]>googleEmbeddings = new ConcurrentHashMap<>();
	 
	 //Using virtual thread to process google 1000 words
	public void process(Map<String, Boolean> googleWords, Map<String, double[]> embeddings) {
	 try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
	      googleWords.keySet().forEach(word -> {
	          pool.submit(() -> {
	              if (embeddings.containsKey(word)) {
	                  googleEmbeddings.put(word, embeddings.get(word)); //Adding word and its embedding
	                  System.out.println("Added to googleEmbeddings: " + word);

	              }
	          });
	      });
	  } catch (Exception e) {
	        System.err.println("Error processing Google-1000 words: " + e.getMessage());
	 }
	}
	  
	 public ConcurrentHashMap<String, double[]> getGoogleEmbeddings() {
	        return googleEmbeddings;
	    }
}



