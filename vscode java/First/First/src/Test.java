import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Test {
    public static Scanner scanner = new Scanner(System.in); // Scanner

    public static String[][] board = new String[4][4]; // board
    public static String[][] cards = new String[4][4]; // cards

    public static void printBoard() {
        for (int i = 0; i < 4; i++) {
            System.out.print("|");
            for (int j = 0; j < 4; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void shuffleCards() {
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("A", "A", "B", "B", "C", "C", "D", "D",
                "E", "E", "F", "F", "G", "G", "H", "H"));
        Collections.shuffle(letters);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cards[i][j] = letters.remove(0);
            }
        }
    }

    public static void checkInput(String[][] cards) {
        while (true) {
            if (!gameover()) {

                int column1, column2, row1, row2;
                do {
                    System.out.println("Choose Column: (1-4)");
                    column1 = scanner.nextInt();
                    System.out.println("Choose Row: (1-4)");
                    row1 = scanner.nextInt();

                    if (column1 < 1 || column1 > 4 || row1 < 1 || row1 > 4) {
                        System.out.println("Invalid input. Please enter numbers within the range 1-4.");
                    }
                } while (column1 < 1 || column1 > 4 || row1 < 1 || row1 > 4);

                // Check if the chosen cell is already selected
                if (!board[row1 - 1][column1 - 1].equals(" _ ")) {
                    System.out.println("Already Entered, Pick Another Combination\n");
                    printBoard();
                    continue;
                } else {
                    board[row1 - 1][column1 - 1] = " " + cards[row1 - 1][column1 - 1] + " ";
                    printBoard();
                }

                do {
                    System.out.println("Choose Column: (1-4)");
                    column2 = scanner.nextInt();
                    System.out.println("Choose Row: (1-4)");
                    row2 = scanner.nextInt();

                    if (column2 < 1 || column2 > 4 || row2 < 1 || row2 > 4) {
                        System.out.println("Invalid input. Please enter numbers within the range 1-4.");
                    }
                } while (column2 < 1 || column2 > 4 || row2 < 1 || row2 > 4);

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
                    }
                }
            } else {
                System.out.println("Game Over!!!");
                break;
            }
        }
        scanner.close();
    }

    public static boolean gameover() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(" _ ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean gameStart = true;
        while (gameStart) {
            System.out.println("Enter n for new game. Enter q to quit the game.");
            String start = scanner.nextLine();
            if (start.equals("q")) {
                System.out.println("Quitting the game");
                break;

            } else if (start.equals("n")) {
                shuffleCards();
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        board[i][j] = " _ ";
                    }
                }

                printBoard();
                checkInput(cards);
                break;

            } else {
                System.out.println("Invalid Character");
                continue;
            }
        }

    }
}
