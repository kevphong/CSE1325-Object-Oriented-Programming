import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static Scanner scanner = new Scanner(System.in); // Scanner

    public static String[][] board = new String[5][5]; // board
    public static String[][] cards = new String[5][5]; // cards

    public static void printBoard() {
        for (int i = 0; i < 5; i++) {
            System.out.print("|");
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void shuffleCards() {
        Random random = new Random();
        ArrayList<String> letters = new ArrayList<String>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        letters.add("J");
        letters.add("K");
        letters.add("L");

        int counter;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                counter = random.nextInt(letters.size());
                cards[i][j] = letters.get(counter);
                letters.remove(counter);
            }
        }
    }

    public static void checkInput(String[][] cards) {
        while (true) {
            if (!gameover()) {
                System.out.println("Choose Row: (1-5)");
                int row1 = scanner.nextInt();
                System.out.println("Choose Column: (1-5)");
                int column1 = scanner.nextInt();

                if (!board[row1 - 1][column1 - 1].equals(" _ ")) {
                    System.out.println("Already Entered, Pick Another Combination\n");
                    printBoard();
                    continue;
                } else {
                    board[row1 - 1][column1 - 1] = " " + cards[row1 - 1][column1 - 1] + " ";
                    printBoard();

                }

                System.out.println("Choose Row: (1-5)");
                int row2 = scanner.nextInt();
                System.out.println("Choose Column: (1-5)");
                int column2 = scanner.nextInt();

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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j].equals(" _ ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
