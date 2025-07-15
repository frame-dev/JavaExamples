package ch.framedev.games.guessthenumber;



/*
 * ch.framedev.guessthenumber
 * =============================================
 * This File was Created by FrameDev
 * Please do not change anything without my consent!
 * =============================================
 * This Class was created at 15.07.2025 17:15
 */

public class GuessTheNumberMain {

    public static void main(String[] args) {
        GuessTheNumber guessTheNumber = new GuessTheNumber(10); // Max 10 turns
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Welcome to Guess The Number!");
        System.out.println("I have selected a number between 1 and 100. Try to guess it!");
        while (!guessTheNumber.isGameOver()) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            String result = guessTheNumber.makeGuess(guess);
            System.out.println(result);
        }
    }
}
