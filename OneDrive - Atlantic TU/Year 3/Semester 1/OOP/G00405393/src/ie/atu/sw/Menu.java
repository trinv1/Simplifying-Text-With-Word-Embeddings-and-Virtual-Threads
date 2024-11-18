package ie.atu.sw;
import java.util.*;
import java.io.*;


/**
 * The Menu class displays the Similarity search menu with each option 1-6
 * It performs an action based on users choice
 * 
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */

public class Menu {

	//Encapsulated fields
    private EmbeddingLoader embeddingLoader;
    private Scanner scanner;
    private String outputFilePath;
    
    /**
     * Constructs a new Menu instance, initializing the embedding loader and scanner.
     */
    public Menu() {
    	embeddingLoader = new EmbeddingLoader();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Start method that initializes menu and performs actions based on user input
     */
    
    public void start() {
    	var embeddingLoader = new EmbeddingLoader();
 
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

        switch(choice) {
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
 
    

 
	


