import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_NUMBER = 100;
    private static final int MIN_NUMBER = 1;

    public static boolean isValidNumber(String text) {
        if (text == null || !text.matches("\\d+")) {
            return false;
        }
        try {
            int number = Integer.parseInt(text);
            return number >= MIN_NUMBER && number <= MAX_NUMBER;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String getGuessResult(int guess, int secretNumber) {
        if (guess < secretNumber) {
            return "Too low. Guess again ";
        } else if (guess > secretNumber) {
            return "Too High. Guess again ";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        boolean guessedCorrectly = false;
        String guessText = getInput("Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ": ");
        int attempts = 0;

        while (!guessedCorrectly) {
            if (!isValidNumber(guessText)) {
                guessText = getInput("I wont count this one, Please enter a number between " + MIN_NUMBER + " to " + MAX_NUMBER + " ");
                continue;
            }

            attempts++;
            int guessedValue = Integer.parseInt(guessText);

            String result = getGuessResult(guessedValue, secretNumber);
            if (result != null) {
                guessText = getInput(result);
            } else {
                System.out.println("You guessed it in " + attempts + " guesses!");
                guessedCorrectly = true;
            }
        }
        scanner.close();
    }
}
