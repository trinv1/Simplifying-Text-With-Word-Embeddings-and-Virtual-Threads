package ie.atu.sw;
import java.util.*;
import java.io.*;


/**
 * The Menu class displays the Similarity search menu with each option 1-6
 * It performs an action based on users choice
 * 
 * <p>
 * This class is responsible for managing user input, handling file paths, and
 * executing the main functionalities of the program.
 * </p>
 * 
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 *///

public class Menu {

	//Encapsulated fields
    private EmbeddingLoader embeddingLoader;
    private Google1000Loader google1000Loader;
    private Scanner scanner;
    private String outputFilePath;
    
    /**
     * Constructs a new Menu instance, initializing the embedding loader and scanner.
     */
    public Menu() {
    	embeddingLoader = new EmbeddingLoader();
    	google1000Loader = new Google1000Loader();
        scanner = new Scanner(System.in);
    }
    
    /**
     * The main menu loop that displays options and performs actions based on user input.
     * The loop continues until the user selects the "Quit" option.
     */
    
    public void start() {
    	var embeddingLoader = new EmbeddingLoader();
    	var google1000Loader = new Google1000Loader();
 
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
		System.out.println("(3) Specify an Output File (default: ./out.txt)");
		System.out.println("(4) Execute, Analyse and Report");
		System.out.println("(5) Optional Extras...");
		System.out.println("(6) Quit");
		
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
            	    	embeddingLoader.load(embeddingsFilePath);
            	        System.out.println("Embeddings file loaded successfully.");
            	    } catch (FileNotFoundException e) {
            	        System.out.println("File not found. Please make sure the file path is correct.");
            	    } catch (Exception e) {
            	        System.out.println(e.getMessage());
            	    }
                break;
                
             //Getting user to enter googleWords file path and loading it into program        	 
            case 2:
            	            	
            	 System.out.print("Enter the path and name of the google words file: ");
            	    String googleWordsFilePath = scanner.nextLine();

            	    try {
            	    	google1000Loader.load(googleWordsFilePath);
            	        System.out.println("Google 1000 Words file loaded successfully.");
            	    } catch (FileNotFoundException e) {
            	        System.out.println("File not found. Please make sure the file path is correct.");
            	    } catch (Exception e) {
            	        System.out.println(e.getMessage());
            	    }
                break;
           
            //Getting user to enter path for output file and generating it if not already created
            case 3:
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
            	System.out.println("Exiting program...");
	            isRunning = false;
	            break;
            	
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        	}  
	    
	    }
	    	scanner.close();	    
    }
}
 
    

 
	


