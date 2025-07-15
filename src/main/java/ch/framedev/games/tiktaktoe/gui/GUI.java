package ch.framedev.games.tiktaktoe.gui;

import ch.framedev.games.tiktaktoe.TikTakToe;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    TikTakToe tikTakToe;

    public GUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tikTakToe = new TikTakToe();

        JLabel statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        JLabel winnerLabel = new JLabel("", SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.PLAIN, 24));

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 3));
        JButton[][] buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(e -> {
                    if (tikTakToe.getGameOver().equals("true") || !buttons[row][col].getText().isEmpty()) {
                        return;
                    }
                    String currentPlayer = tikTakToe.getPlayer();
                    if (tikTakToe.makeMove(row, col)) {
                        buttons[row][col].setText(currentPlayer);
                        buttons[row][col].setEnabled(false);
                        if (tikTakToe.getGameOver().equals("true")) {
                            if ("Draw".equals(tikTakToe.getWinner())) {
                                winnerLabel.setText("It's a draw!");
                            } else {
                                winnerLabel.setText("Player " + tikTakToe.getWinner() + " wins!");
                            }
                            statusLabel.setText("");
                            for (JButton[] buttonRow : buttons) {
                                for (JButton button : buttonRow) {
                                    button.setEnabled(false);
                                }
                            }
                        } else {
                            statusLabel.setText("Player " + tikTakToe.getPlayer() + "'s turn");
                        }
                    }
                });
                panel.add(buttons[i][j]);
            }
        }

        add(statusLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        JButton resetButton = getResetJButton(buttons, statusLabel, winnerLabel);

        // Add the reset button to the bottom of the frame
        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(winnerLabel, BorderLayout.CENTER);
        southPanel.add(resetButton, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton getResetJButton(JButton[][] buttons, JLabel statusLabel, JLabel winnerLabel) {
        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 20));
        resetButton.addActionListener(e -> {
            tikTakToe = new TikTakToe(); // Re-initialize the game logic
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                }
            }
            statusLabel.setText("Player X's turn");
            winnerLabel.setText("");
        });
        return resetButton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}