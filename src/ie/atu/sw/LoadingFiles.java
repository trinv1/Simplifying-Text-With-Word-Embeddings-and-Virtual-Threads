package ie.atu.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * The LoadingFiles class is responsible for creating virtual threads to handle 
 * loading in Googles 1000 most commonly used words 
 * and Word Embeddings from their respective files concurrently.
 * Both are stored in ConcurrentHashMaps from thread safe access
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */
/*public class LoadingFiles {
    
	//Encapsulated Strings
    private String embeddingsFilePath;
    private String googleFilePath;
    
    //File objects
    EmbeddingProcessor embeddingProcessor = new EmbeddingProcessor();
    GoogleProcessor googleProcessor = new GoogleProcessor();
    
 
    public LoadingFiles(String embeddingsFilePath, String googleFilePath) {
        this.embeddingsFilePath = embeddingsFilePath;
        this.googleFilePath = googleFilePath;
    }
 
    public void loadFiles() {
	    try(var es = Executors.newVirtualThreadPerTaskExecutor()){
	    	 
	    	//Loading embeddings file
            ex.submit(() -> {
                try {
                    embeddingProcessor.load(embeddingsFilePath);
                    System.out.println("Embeddings loaded successfully.");
                } catch (Exception e) {
                    System.err.println("Error loading embeddings: " + e.getMessage());
                }
            });
            
          //Loading google file
            ex.submit(() -> {
                try {
                    googleProcessor.load(googleFilePath);
                    System.out.println("Embeddings loaded successfully.");
                } catch (Exception e) {
                    System.err.println("Error loading embeddings: " + e.getMessage());
                }
            });
            
            //Executor closing after tasks done
        } catch (Exception e) {
            System.err.println("Error with virtual threads: " + e.getMessage());
        }
	    
	    }
 
    public EmbeddingProcessor getEmbeddingProcessor() {
        return embeddingProcessor;
    }


    public GoogleProcessor getGoogleProcessor() {
        return googleProcessor;
    }
    
    }
    */


    
    

