package ch.framedev.games.tiktaktoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TikTakToe game = new TikTakToe();
        Scanner scanner = new Scanner(System.in);

        while (game.getGameOver().equals("false")) {
            System.out.println("Current board:");
            printBoard(game.getBoard());
            System.out.println("Player " + game.getPlayer() + ", enter your move (row and column, 1-3): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            if (!game.makeMove(row, col)) {
                System.out.println("Invalid move, try again.");
            }
        }
        printBoard(game.getBoard());
        if (game.getWinner().equals("Draw")) {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + game.getWinner() + " wins!");
        }
        scanner.close();
    }

    private static void printBoard(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] == null ? "." : board[i][j]);
                if (j < 2) System.out.print(" ");
            }
            System.out.println();
        }
    }
}