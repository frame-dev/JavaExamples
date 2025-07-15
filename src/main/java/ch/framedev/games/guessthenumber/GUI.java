package ch.framedev.games.guessthenumber;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    private GuessTheNumber game;
    private final JButton guessButton;
    private final JTextField guessField;
    private final JTextArea resultArea;

    public GUI() {
        setTitle("Guess The Number");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        game = new GuessTheNumber(10); // Max 10 turns

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        guessField = new JTextField();
        guessField.setSize(250, 20);
        guessButton = new JButton("Guess");
        guessButton.setSize(100, 20);
        JButton resetButton = new JButton("Reset");
        resultArea = new JTextArea();
        resultArea.setSize(250, 30);
        resultArea.setEditable(false);

        guessField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        guessButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        resetButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        resultArea.setRows(6);

        guessButton.addActionListener(e -> {
            String guessText = guessField.getText();
            try {
                int guess = Integer.parseInt(guessText);
                String result = game.makeGuess(guess);
                resultArea.append(result + "\n");
                if (game.isGameOver()) {
                    guessButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                resultArea.append("Please enter a valid number.\n");
            }
            guessField.setText("");
        });

        resetButton.addActionListener(e -> {
            game = new GuessTheNumber(10);
            resultArea.setText("");
            guessButton.setEnabled(true);
            guessField.setText("");
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(resetButton);
        panel.add(new JScrollPane(resultArea));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}