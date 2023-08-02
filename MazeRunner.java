package assignment1;

import java.util.Scanner;
import assignment1.timer;
import java.util.*;

public class MazeRunner {

    // Static variables so that they could be accessed by all classes and modified in run time
    static int highScore;
    static char[][] maze;
    static int rowEnd;
    static int colEnd;

    public static void main(String[] args) {

        // Variable declaration to store user input
        Scanner userInput = new Scanner(System.in);
        String selection;

        // Main loop until user presses 'e' to Exit
        do {
            // Main Menu
            System.out.println("Choose any option from a to e");
            System.out.println("a. Play the Game ");
            System.out.println("b. Instructions");
            System.out.println("c. Credits");
            System.out.println("d. High Score");
            System.out.println("e. Exit");
            // Initializing input variable "selection"
            selection = userInput.nextLine();
            // Performs respective functions through switch considering option chosen by user
            // Uses toLowerCase method to eliminate miscommunication from user
            switch (selection.toLowerCase()) {
                case "a":
                    // Game
                    playGame(userInput);
                    break;
                case "b":
                    showInstructions();
                    break;
                case "c":
                    showCredits();
                    break;
                case "d":
                    showHighScore();
                    break;
                case "e":
                    exitGame();
                    break;
                default:
                    System.out.println("Invalid, PLease Try again!");
                    break;
            }
        } while (!selection.equals("e"));

        userInput.close();

    }

    // Method: Creates an Easy Maze
    public static char[][] createEasyMaze() {

        // Declares array
        char[][] easyMaze = new char[6][6];

        // Initializes and declares array
        // For loop for row 1
        for (int j = 0; j < 6; j++) {
            switch (j) {
                case 0:
                case 1:
                case 2:
                case 3:
                    easyMaze[0][j] = '#';
                    break;
                default:
                    break;
            }
        }

        // Manual index inputs of walls
        easyMaze[1][0] = '#';
        easyMaze[1][1] = '#';
        easyMaze[1][5] = '#';
        easyMaze[2][0] = '#';
        easyMaze[2][1] = '#';
        easyMaze[2][2] = '#';
        easyMaze[2][4] = '#';
        easyMaze[2][5] = '#';
        easyMaze[3][2] = '#';
        easyMaze[3][4] = '#';
        easyMaze[3][5] = '#';
        easyMaze[4][0] = '#';
        easyMaze[4][5] = '#';

        // For loop for row 6 inputs
        for (int j = 0; j < 6; j++) {
            switch (j) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    easyMaze[5][j] = '#';
                    break;
                default:
                    break;
            }
        }

        // Manual inputs of '.'
        easyMaze[0][4] = '.';
        easyMaze[1][2] = '.';
        easyMaze[1][3] = '.';
        easyMaze[1][4] = '.';
        easyMaze[2][3] = '.';
        easyMaze[3][1] = '.';
        easyMaze[3][3] = '.';
        easyMaze[4][1] = '.';
        easyMaze[4][2] = '.';
        easyMaze[4][3] = '.';
        easyMaze[4][4] = '.';

        // Manual inputs of Starting point and Ending point
        easyMaze[3][0] = 'P';
        easyMaze[0][5] = 'E';
        rowEnd = 0;
        colEnd = 5;

        // Returns Maze
        return easyMaze;
    }

    // Method: Displays the Current Maze
    public static void displayMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method: Checks validity of the move
    public static boolean isValidMove(int newX, int newY) {
        // Checks if the move is within the maze (matrix), and also not hitting any wall/obstacle ('#')
        if (newX < 0 || newX >= maze.length || newY < 0 || newY >= maze[0].length || maze[newX][newY] == '#') {
            return false;
        } else {
            return true;
        }
    }

    // Method: Moves the character and updates the maze
    public static char[][] movePlayer(String move) {
        // First finds the character's current position i.e. 'P'
        // Initializing rowPosition and colPosition as -1(default position)
        int rowPosition = 0;
        int colPosition = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'P') {
                    rowPosition = i;
                    colPosition = j;
                    break;
                }
            }
        }

        // Updates character position using switch after checking validity of the move
        switch (move) {
            case "w":
                if (isValidMove(rowPosition - 1, colPosition) == true) {
                    maze[rowPosition][colPosition] = '.';
                    maze[rowPosition - 1][colPosition] = 'P';
                } else {
                    System.out.println("Invalid move! try again");
                }
                break;
            case "a":
                if (isValidMove(rowPosition, colPosition - 1) == true) {
                    maze[rowPosition][colPosition] = '.';
                    maze[rowPosition][colPosition - 1] = 'P';
                } else {
                    System.out.println("Invalid move! try again");
                }
                break;
            case "s":
                if (isValidMove(rowPosition + 1, colPosition) == true) {
                    maze[rowPosition][colPosition] = '.';
                    maze[rowPosition + 1][colPosition] = 'P';
                } else {
                    System.out.println("Invalid move! try again");
                }
                break;
            case "d":
                if (isValidMove(rowPosition, colPosition + 1) == true) {
                    maze[rowPosition][colPosition] = '.';
                    maze[rowPosition][colPosition + 1] = 'P';
                } else {
                    System.out.println("Invalid move! try again");
                }
                break;
            default:
                System.out.println("Invalid input. Try Again");
                break;
        }
        // Returns updated maze
        return maze;
    }

    // Method: checks if player has won
    public static boolean hasPlayerWon() {
        // Finds current position of player
        int rowPosition = 0;
        int colPosition = 0;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'P') {
                    rowPosition = i;
                    colPosition = j;
                    break;
                }
            }
        }

        // Compares with endPoints
        if (rowPosition == rowEnd && colPosition == colEnd) {
            System.out.println("Congratulations! You have won");
            return true;
        } else {
            return false;
        }
    }

    // This method contains the game and returns score
    public static void playGame(Scanner userInput) {
        // Variable to give input for startNewGame method
        boolean startNew = false;

        maze = createEasyMaze();

        System.out.println("Game started. complete it within 90 seconds");
        System.out.println("Enter 'r' to return to the main menu ");

        // Timer started
        timer.main(null);
        // Game starts here
        String move;
        int steps = 0;

        do {
            // Displays the current state of the maze
            displayMaze();

            // User input for move
            move = userInput.nextLine();

            // Checks if the user wants to exit the game
            if (move.equals("r")) {
                System.out.println("Returning to the main menu");
                // Stops timer
                timer.stopTimer();
                return;
            }

            // Moves the player by updating the current maze
            movePlayer(move);
            steps += 1;

        } while (!hasPlayerWon());

        // Stops timer when the player has won
        timer.stopTimer();
        // Updates score and stores it in the variable score
        int time = (int) timer.timeTaken; // Converting time taken (long) to int data type
        int score = updateScore(steps, time);
        // Displays the result
        displayScore(score, steps, time);

        // Input prompt
        System.out.println("Would you like to start a new game?");
        System.out.println("Press 1 for Yes");
        System.out.println("Press 2 for No");
        int c = userInput.nextInt();

        if (c == 2) {
            startNew = false;
        } else if (c == 1) {
            startNew = true;
        } else if (c != 1 && c != 2) {
            System.out.println("Sorry, invalid response!");
            startNew = false;
        }
        while (startNew);
    }

    // Method: displays the result of the game
    public static void displayScore(int score, int steps, int time) {
        // Get moves made to finish the game
        // 10 points per move
        System.out.println("You made " + steps + " number of moves");
        System.out.println("You took " + time + " seconds to reach the endpoint");
        System.out.println("Your New score : " + score);
    }

    // Method: updates score and highscore
    public static int updateScore(int steps, int timeTaken) {
        int score;
        // (maxscore - (50 * time taken to finish the game)) + (maxscore - (moves made * 100))
        // 50 points deduction per second spent         100 points deduction per move made
        score = (1800 - (20 * timeTaken)) + (1800 - (steps * 100));

        if (score > highScore) {
            highScore = score;
        }

        return score;
    }

    // Method: resets maze and makes it ready for a new game.
    public static void startNewGame(boolean startNew) {
        if (startNew) {
            // Resets the maze, rowEnd, colEnd
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    maze[i][j] = ' ';
                }
            }
            rowEnd = 0;
            colEnd = 0;
        }
    }

    // Method: displays instructions
    public static void showInstructions() {
        System.out.println("Use 'w','a','s','d' to move up, left, down, and right, respectively");
        System.out.println("Avoid walls and reach the end point as fast as you can");
        System.out.println("If you achieve this you will win the game");
    }

    // Method: display credits
    public static void showCredits() {
        System.out.println("MazeRunner Game made by Muhammad Saud Amer for Java");
    }

    // Method: displays the top score
    public static void showHighScore() {
        System.out.println("Your top score is " + highScore);
    }

    // Method: Exits the game
    public static void exitGame() {
        System.out.println("Game Exited");
    }
}
