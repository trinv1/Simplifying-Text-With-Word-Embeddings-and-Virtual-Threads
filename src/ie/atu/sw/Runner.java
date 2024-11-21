package ie.atu.sw;

/**
 * The Runner class is the entry point for the Text Simplifier project
 * It initializes the menu and calls the start method to begin the program
 * It holds and calls a method to a display progress meter
 *
 * @author Trin Villaruel
 * @version 4.29
 * @since 1.8
 */

public class Runner {

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to your Virtual Thread Simplifier!");

            Menu menu = new Menu();//Initializing the menu 
            menu.start();
            
            displayProgressMeter(); //Displaying the progress meter 


        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Method to display a progress meter
     * Running Time: 0(n)
     * Method iterates over a loop w/ "n" iterations where n's the value of the size variable
     * 
     */
    public static void displayProgressMeter() {// Method displaying the progress meter
    	
    	System.out.print(ConsoleColour.GREEN);   
        int size = 100;                         
        for (int i = 0; i < size; i++) {        
            printProgress(i + 1, size);         
            try {
                Thread.sleep(10);               
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   	/**
	 * Method to gradually print progress
	 * Running time: 0(1)
	 * Method does a series of constant time operations regardless of input size
	 * It doesnt have any loops and all operations in it execute in constant time
	 */
    public static void printProgress(int index, int total) {
    	
        if (index > total) return;    
        int size = 50;                
        char done = '\\';          
        char todo = '0';            

        int complete = (100 * index) / total;
        int completeLen = size * complete / 100;

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append((i < completeLen) ? done : todo);
        }

        System.out.print("\r" + sb + "] " + complete + "%");//Printing the progress bar

        if (complete == 100) System.out.println();//once the progress reaches its max, moving to a new line
    }
}
