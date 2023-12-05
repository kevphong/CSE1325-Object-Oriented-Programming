import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class MemoryGame {
    private static final int BOARD_SIZE = 4;
    private Scanner scanner = new Scanner(System.in);

    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
    private String[][] cards = new String[BOARD_SIZE][BOARD_SIZE];
    private int lives = 10;
    private int moves = 0;
    private long startTime;

    // Constructor for MemoryGame class
    public MemoryGame() {
        initializeBoard();
        shuffleLetters();
        printBoard();
    }

    // Creates game board with placeholders, underscores
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], " _ ");
        }
    }

    // Prints current game board
    public void printBoard() {
        for (String[] row : board) {
            System.out.print("|");
            for (String cell : row) {
                System.out.print(cell);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    // Shuffles letters and assigns them to a spot
    public void shuffleLetters() {
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("A", "A", "B", "B", "C", "C", "D", "D",
                "E", "E", "F", "F", "G", "G", "H", "H"));
        Collections.shuffle(letters);

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.arraycopy(letters.toArray(), i * BOARD_SIZE, cards[i], 0, BOARD_SIZE);
        }
    }

    // Gets user input and makes sure input is within range
    private int getUserInput(String prompt, int minValue, int maxValue) {
        int userInput;
        do {
            System.out.println(prompt);
            userInput = scanner.nextInt();

            if (userInput < minValue || userInput > maxValue) {
                System.out.println(
                        "Invalid input. Please enter numbers within the range " + minValue + "-" + maxValue + ".");
            }
        } while (userInput < minValue || userInput > maxValue);

        return userInput;
    }

    // Checks if the game is over by analyzing board to see if there are any more
    // hidden letters on the board
    private boolean gameOver() {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell.equals(" _ ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Reveals all the cards on the board
    public void revealAllCards() {
        System.out.println("Revealing all cards:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = " " + cards[i][j] + " ";
            }
        }
        printBoard();
    }

    // Hides all the cards on the board
    public void hideAllCards() {
        System.out.println("Hiding all cards:");
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], " _ ");
        }
        printBoard();
    }

    // Resets the game by shuffling letters, resetting lives, and initializing the
    // board
    public void resetGame() {
        System.out.println("Resetting the game...");
        shuffleLetters();
        lives = 10;
        moves = 0;
        initializeBoard();
        printBoard();
    }

    // Displays the remaining lives
    public void displayRemainingLives() {
        System.out.println("Lives remaining: " + lives);
    }

    // Start the timer when the game begins
    private void startTimer() {
        startTime = System.currentTimeMillis();
    }

    // Stop the timer when the game ends
    private void stopTimer() {
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken: " + elapsedTime / 1000 + " seconds");
    }

    // Increment the moves counter after each valid move
    private void incrementMoves() {
        moves++;
    }

    // Display the total moves at the end of the game
    private void displayTotalMoves() {
        System.out.println("Total Moves: " + moves);
    }

    // Display game statistics at the end of the game
    private void displayGameStats() {
        displayTotalMoves();
        displayRemainingLives();
    }

    // Manages the game, while also displaying game status, such as correct and
    // wrong combinations, as well as lives remaining.
    public void takeUserInput() {
        startTimer();

        while (lives > 0) {
            if (!gameOver()) {
                int column1 = getUserInput("Choose Column: (1-4)", 1, BOARD_SIZE);
                int row1 = getUserInput("Choose Row: (1-4)", 1, BOARD_SIZE);

                if (!board[row1 - 1][column1 - 1].equals(" _ ")) {
                    System.out.println("Already Entered, Pick Another Combination\n");
                    printBoard();
                    continue;
                } else {
                    board[row1 - 1][column1 - 1] = " " + cards[row1 - 1][column1 - 1] + " ";
                    printBoard();
                }

                int column2 = getUserInput("Choose Column: (1-4)", 1, BOARD_SIZE);
                int row2 = getUserInput("Choose Row: (1-4)", 1, BOARD_SIZE);

                if (!board[row2 - 1][column2 - 1].equals(" _ ")) {
                    System.out.println("Already Entered, Pick Another Combination\n");
                    board[row1 - 1][column1 - 1] = " _ ";
                    printBoard();
                    continue;
                } else {
                    board[row2 - 1][column2 - 1] = " " + cards[row2 - 1][column2 - 1] + " ";

                    if (board[row1 - 1][column1 - 1].equals(board[row2 - 1][column2 - 1])) {
                        printBoard();
                        System.out.println("Correct Choice!");
                    } else {
                        printBoard();
                        System.out.println("Incorrect Choice");
                        board[row1 - 1][column1 - 1] = " _ ";
                        board[row2 - 1][column2 - 1] = " _ ";
                        printBoard();
                        lives--;
                        System.out.println("Lives remaining: " + lives);
                    }

                    incrementMoves(); // Increment moves after each valid move
                }
            } else {
                System.out.println("Congratulations! You Won!");
                break;
            }
        }

        if (lives == 0) {
            System.out.println("Game Over! You ran out of lives.");
        }

        stopTimer();
        displayGameStats();
        scanner.close();
    }
}