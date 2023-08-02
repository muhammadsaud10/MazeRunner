package assignment1; // Package declaration

import assignment1.MazeRunner; // Importing the MazeRunner class from the assignment1 package

public class timer {
	
static long timeTaken; // Declaring a static variable to store the time taken

static Thread thread; // Declaring a static variable to hold a thread reference

	public static void main(String[] args) { // Main method, the entry point of the program
		
		timeTaken = 0; // Initializing the timeTaken variable to 0
		
        long startTime = System.currentTimeMillis();  // Getting the current time in milliseconds

        thread = new Thread(new Runnable() { // Creating a new thread with an anonymous inner class
        	
            public void run() {
            	
            	MazeRunner mazeRunner = new MazeRunner(); //creates instance of class MazeRunner

            	if(mazeRunner.hasPlayerWon()) { // Checking if the player has won the game
            		
	                long endTime = System.currentTimeMillis(); // Getting the current time again
	                
	                timeTaken = (endTime - startTime)/1000; // Calculating the time taken in seconds

            	}
            }
        });

        thread.start();
        try {
            thread.join(30000); // Wait for up to 30 seconds for the thread to finish
        } catch (InterruptedException e) { // Handling the InterruptedException if it occurs
            e.printStackTrace();
        }

        if (thread.isAlive()) { // If the thread is still alive (not finished within 30 seconds)
            thread.interrupt(); // Interrupt the thread
            System.out.println("Time up. You lost!"); // Print a message indicating time is up and the player lost
            System.exit(0);
        }       
    }
	
	//inner class to stop timer
	public class Timer {
		
	    private static Thread thread; // Declaring a static variable to hold a thread reference

	    public static void stopTimer() { // Method to stop the timer
	    	
	        if (thread != null && thread.isAlive()) { // Check if the thread is not null and still alive
	        	
	            thread.interrupt(); // Interrupt the thread to stop it
	        }
	    }
	}

	public static void stopTimer() {		
	}
}


