package ie.atu.sw;
import java.util.*;

import java.io.*;


/**
 * The Menu is class is responsible for displaying the Virtual Threads Text Simplifier menu 
 * with each option 1-7 & it performs an action based on users choice.
 * 
 * <p>
 * This class is responsible for managing user input, handling file paths, and
 * executing the main functionalities of the program.
 * </p>
 * 
 * @author Trin Villaruel
 * @version 2.1
 * @since 19
 *///

public class Menu {

	//Encapsulated fields
    private EmbeddingProcessor embeddingProcessor;
    private TextFileProcessor textFileProcessor;
    private GoogleProcessor googleProcessor;
    private GoogleEmbeddings googleEmbeddings;
    private Scanner scanner;
    private String outputFilePath;
    
    /**
     * Constructs a new Menu instance, initializing the embedding loader and scanner.
     */
    public Menu() {
    	embeddingProcessor = new EmbeddingProcessor();
    	googleProcessor = new GoogleProcessor();
    	textFileProcessor = new TextFileProcessor();
        googleEmbeddings  = new GoogleEmbeddings();
        scanner = new Scanner(System.in);
    }
    
    /**
     * The main menu loop that displays options and performs actions based on user input.
     * The loop continues until the user selects the "Quit" option.
     */
    
    public void start() {
    	var embeddingProcessor = new EmbeddingProcessor();
    	var googleProcessor = new GoogleProcessor();
    	var googleEmbeddings = new GoogleEmbeddings();
    	var textFileProcessor = new TextFileProcessor();
 
	    boolean isRunning = true;
	
	    while(isRunning) {
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*         	  Virtual Thread Text Simplifier		       *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Embeddings File");
		System.out.println("(2) Specify Google 1000 File");
		System.out.println("(3) Specify Text File");
		System.out.println("(4) Specify an Output File (default: ./out.txt)");
		System.out.println("(5) Execute, Analyse and Report");
		System.out.println("(6) Optional Extras...");
		System.out.println("(7) Quit");
		
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-6]>");
		System.out.println();
	    
		int choice = scanner.nextInt();
		scanner.nextLine();
		
		//Actions performed based on user input
        switch(choice) {
        	
        	//Getting user to enter embeddings file path and loading it into program       	 
        	case 1:
	            System.out.print("Enter the path and name of the embeddings file: ");
	            String embeddingsFilePath = scanner.nextLine();
	
	            try {
        	    	embeddingProcessor.load(embeddingsFilePath);
        	        System.out.println("Embeddings file loaded successfully.");
        	    } catch (FileNotFoundException e) {
        	        System.out.println("File not found. Please make sure the file path is correct.");
        	    } catch (Exception e) {
        	        System.out.println(e.getMessage());
        	    }
	            
              /*  var EmbeddingsMap = embeddingProcessor.getEmbeddings();

	            
	            // Displaying the contents of the map
                EmbeddingsMap.forEach((word, embedding) -> {
                    System.out.print("Word: " + word + " | Embedding: ");
                    System.out.println(java.util.Arrays.toString(embedding));
                });*/
            break;

                
          //Getting user to enter google file path and loading it into program      	 
        	case 2:
	            System.out.print("Enter the path and name of the Google 1000 Word file: ");
	            String googleFilePath = scanner.nextLine();
	
	            try {
        	    	googleProcessor.load(googleFilePath);
        	        System.out.println("Google 1000 words file loaded successfully.");
        	    } catch (FileNotFoundException e) {
        	        System.out.println("File not found. Please make sure the file path is correct.");
        	    } catch (Exception e) {
        	        System.out.println(e.getMessage());
        	    }
            break;
            
          //Getting user to enter text file path and loading it into program with thread       	 
        	case 3:
	            System.out.print("Enter the path and name of text file: ");
	            String textFilePath = scanner.nextLine();
	
	            try {
					textFileProcessor.load(textFilePath);
        	        System.out.println("Text file loaded successfully.");
        	    } catch (FileNotFoundException e) {
        	        System.out.println("File not found. Please make sure the file path is correct.");
        	    } catch (Exception e) {
        	        System.out.println(e.getMessage());
        	    }
            break;
           
            //Getting user to enter path for output file and generating it if not already created
            case 4:
            	System.out.print("Enter the path for the output file ( default: './out.txt'): ");
                 outputFilePath = scanner.nextLine();

                try {
                    File file = new File(outputFilePath);// Creating a File object using the specified path
                                        
                    if (file.createNewFile()) {  // Creating the file if it doesn't exist
                        System.out.println("File created: " + file.getName());
                    } else {
                        System.out.println("File already exists: " + file.getName());
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                }              
                break;
            
            //Exiting program
            case 6:  

            	//Calling p
                googleEmbeddings.process(googleProcessor.getGoogleWords(), embeddingProcessor.getEmbeddings());
                var googleEmbeddingsMap = googleEmbeddings.getGoogleEmbeddings();
                

                // Display the contents of the map
                googleEmbeddingsMap.forEach((word, embedding) -> {
                    System.out.print("Word: " + word + " | Embedding: ");
                    System.out.println(Arrays.toString(embedding));
                });
	            break;
            	
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        	}  
	    
	    }
	    	scanner.close();	    
    }
}
 
    

 
	


