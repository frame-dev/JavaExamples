package ch.framedev.games.tiktaktoe;

/**
 * TikTakToe is a simple implementation of the classic Tic-Tac-Toe game.
 * It allows two players to take turns placing their marks (X or O) on a 3x3 grid.
 * The game checks for a winner after each move and declares the game over when a player wins or when there is a draw.
 * The game can be played in a console or can be extended for a GUI application.
 */
public class TikTakToe {

    /**
     * The current turn number, starting from 0.
     * It increments after each valid move.
     */
    private int turn;
    private String player;
    private final String[][] board;
    private final String[][] winConditions;
    private String gameOver;
    private String winner;

    public TikTakToe() {
        this.turn = 0;
        this.player = "X";
        this.board = new String[3][3];
        this.winConditions = new String[][] {
                {"00", "01", "02"},
                {"10", "11", "12"},
                {"20", "21", "22"},
                {"00", "10", "20"},
                {"01", "11", "21"},
                {"02", "12", "22"},
                {"00", "11", "22"},
                {"02", "11", "20"}
        };
        this.gameOver = "false";
        this.winner = "";
    }

    public int getTurn() {
        return turn;
    }

    public String getPlayer() {
        return player;
    }

    public String[][] getBoard() {
        return board;
    }

    public String getGameOver() {
        return gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public void nextTurn() {
        turn++;
        player = player.equals("X") ? "O" : "X";
    }

    public boolean makeMove(int row, int col) {
        if (board[row][col] == null && gameOver.equals("false")) {
            board[row][col] = player;
            checkWinner();
            if (gameOver.equals("false")) {
                nextTurn();
            }
            return true;
        }
        return false;
    }

    private void checkWinner() {
        for (String[] condition : winConditions) {
            String a = board[Character.getNumericValue(condition[0].charAt(0))][Character.getNumericValue(condition[0].charAt(1))];
            String b = board[Character.getNumericValue(condition[1].charAt(0))][Character.getNumericValue(condition[1].charAt(1))];
            String c = board[Character.getNumericValue(condition[2].charAt(0))][Character.getNumericValue(condition[2].charAt(1))];
            if (a != null && a.equals(b) && b.equals(c)) {
                gameOver = "true";
                winner = a;
                return;
            }
        }
        // Check for draw
        boolean draw = true;
        for (String[] row : board) {
            for (String cell : row) {
                if (cell == null) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            gameOver = "true";
            winner = "Draw";
        }
    }
}