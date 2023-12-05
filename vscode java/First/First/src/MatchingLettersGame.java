import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MatchingLettersGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfTurns = 10; // Number of turns the player has
        List<Character> letters = generateRandomLetters();
        List<Character> placeholders = new ArrayList<>(Collections.nCopies(letters.size(), ' '));
        boolean gameOver = false;

        System.out.println("Welcome to the Matching Letters Game!");

        while (numberOfTurns > 0 && !gameOver) {
            displayGameBoard(placeholders);
            System.out.println("Turns left: " + numberOfTurns);

            int firstIndex = getInput("Enter the first placeholder index: ", scanner, placeholders.size());
            int secondIndex = getInput("Enter the second placeholder index: ", scanner, placeholders.size());

            if (letters.get(firstIndex) == letters.get(secondIndex)) {
                placeholders.set(firstIndex, letters.get(firstIndex));
                placeholders.set(secondIndex, letters.get(secondIndex));
                System.out.println("Match!");
            } else {
                System.out.println("Mismatch! You lose a turn.");
                numberOfTurns--;
            }

            if (Collections.frequency(placeholders, ' ') == 0) {
                gameOver = true;
            }
        }

        if (gameOver) {
            System.out.println("Congratulations! You've matched all the letters!");
        } else {
            System.out.println("Game over. You've run out of turns.");
        }

        scanner.close();
    }

    private static List<Character> generateRandomLetters() {
        List<Character> letters = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            letters.add(c);
        }
        Collections.shuffle(letters);
        letters = letters.subList(0, 10); // Select 10 random letters
        letters.addAll(letters); // Create pairs of letters
        Collections.shuffle(letters);
        return letters;
    }

    private static void displayGameBoard(List<Character> placeholders) {
        System.out.println("Game Board:");
        for (int i = 0; i < placeholders.size(); i++) {
            System.out.print(placeholders.get(i) + " ");
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
    }

    private static int getInput(String prompt, Scanner scanner, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
            if (input < 0 || input >= max) {
                System.out.println("Invalid input. Please enter a valid index.");
            }
        } while (input < 0 || input >= max);
        return input;
    }
}
