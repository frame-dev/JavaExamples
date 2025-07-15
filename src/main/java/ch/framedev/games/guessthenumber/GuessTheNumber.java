package ch.framedev.games.guessthenumber;



/*
 * ch.framedev.guessthenumber
 * =============================================
 * This File was Created by FrameDev
 * Please do not change anything without my consent!
 * =============================================
 * This Class was created at 15.07.2025 17:14
 */

public class GuessTheNumber {

    private int turn;
    private final int numberToGuess;
    private final int maxTurns;
    private boolean gameOver;

    public GuessTheNumber(int maxTurns) {
        this.maxTurns = maxTurns;
        this.turn = 0;
        this.gameOver = false;
        this.numberToGuess = (int) (Math.random() * 100) + 1; // Random number between 1 and 100
    }

    public String makeGuess(int guess) {
        if(gameOver) {
            return "Game is already over. No more guesses allowed.";
        }
        turn++;
        if(guess < 1 || guess > 100) {
            return "Guess must be between 1 and 100.";
        }
        if(guess == numberToGuess) {
            gameOver = true;
            return "Congratulations! You've guessed the number " + numberToGuess + " in " + turn + " turns.";
        } else if(turn >= maxTurns) {
            gameOver = true;
            return "Game over! You've used all your turns. The number was " + numberToGuess + ".";
        } else if(guess < numberToGuess) {
            return "Too low! Try again.";
        } else {
            return "Too high! Try again.";
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
