import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final Scanner scanner = new Scanner(System.in);

    public static boolean isValidNumber(String text) {
        if (text == null || !text.matches("\\d+")) {
            return false;
        }
        try {
            int number = Integer.parseInt(text);
            return number >= 1 && number <= 100;
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
        int secretNumber = random.nextInt(100) + 1;
        boolean guessedCorrectly = false;
        String guessText = getInput("Guess a number between 1 and 100: ");
        int attempts = 0;

        while (!guessedCorrectly) {
            if (!isValidNumber(guessText)) {
                guessText = getInput("I wont count this one, Please enter a number between 1 to 100 ");
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
