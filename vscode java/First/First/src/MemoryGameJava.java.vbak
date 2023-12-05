import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class MemoryGame {
    private static final int BOARD_SIZE = 4;
    private Scanner scanner = new Scanner(System.in);

    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
    private String[][] cards = new String[BOARD_SIZE][BOARD_SIZE];
    private int lives = 10;

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

    // Manages the game, while also displaying game status, such as correct and
    // wrong combinations, as well as lives remaining.
    public void takeUserInput() {
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
                }
            } else {
                System.out.println("Congratulations! You Won!");
                break;
            }
        }
        if (lives == 0) {
            System.out.println("Game Over! You ran out of lives.");
        }
        scanner.close();
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
}

public class MemoryGameJava {
    public static void main(String[] args) {

        // Initializae MemoryGame class to start the game
        MemoryGame memoryGame = new MemoryGame();
        memoryGame.takeUserInput();
    }
}
