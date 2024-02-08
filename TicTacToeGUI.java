package tic_tac_toe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGUI extends JFrame {
	private JButton[][] cells;
	private JLabel statusLabel;
	private boolean player1Turn;
	private boolean gameEnded;
	private int numMoves;
	private Player player1, player2;

	public TicTacToeGUI(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
		setTitle("GAME: Tic Tac Toe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up grid of buttons
		JPanel gridPanel = new JPanel(new GridLayout(3, 3));
		cells = new JButton[3][3];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cells[row][col] = new JButton("");
				cells[row][col].setFont(new Font("Arial", Font.BOLD, 50));
				cells[row][col].setBackground(new Color(47, 104, 121));
				cells[row][col].setForeground(new Color(124, 221, 210));
				final int r = row, c = col;
				cells[row][col].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						buttonClicked(r, c);
					}
				});
				gridPanel.add(cells[row][col]);
			}
		}
		add(gridPanel);

		// Set up status label
		statusLabel = new JLabel(player1.getName() + "'s turn (O)");
		statusLabel.setFont(new Font("Arial", Font.BOLD, 35));
		getContentPane().setBackground(new Color(172, 198, 197));
		statusLabel.setForeground(new Color(47, 104, 121));
		statusLabel.setPreferredSize(
				new Dimension(statusLabel.getPreferredSize().width + 150, statusLabel.getPreferredSize().height + 6));
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		add(statusLabel, BorderLayout.SOUTH);

		setSize(650, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buttonClicked(int row, int col) {
		if (gameEnded) {
			JOptionPane.showMessageDialog(this, "Game has ended. Please start a new game.");
			return;
		}

		// Check if cell is empty
		if (!cells[row][col].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Cell is already taken. Please choose another cell.");
			return;
		}

		// Make move
		if (player1Turn) {
			cells[row][col].setText("X");
			statusLabel.setText(player1.getName() + "'s turn (O)");
		} else {
			cells[row][col].setText("O");
			statusLabel.setText(player2.getName() + "'s turn (X)");
		}
		numMoves++;

		// Check for winner or draw
		if (numMoves >= 5) {
			if (checkWinner(row, col)) {
				gameEnded = true;
				String winner = player1Turn ? player2.getName() : player1.getName();
				statusLabel.setText("CONGRATS " + winner + "!!");
				JOptionPane.showMessageDialog(this, "Congratulations " + winner + ", you won!");

				int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					resetGame();
				} else {
					dispose();
				}
			} else if (numMoves == 9) {
				gameEnded = true;
				statusLabel.setText("DRAW!!");
				JOptionPane.showMessageDialog(this, "Game is a draw.");
				int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again?",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					resetGame();
				} else {
					dispose();
				}
			}
		}

		player1Turn = !player1Turn;
	}

	private void resetGame() {
		player1Turn = true;
		gameEnded = false;
		numMoves = 0;

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				cells[row][col].setText("");
				cells[row][col].setBackground(new Color(47, 104, 121));
			}
		}

		statusLabel.setText(player1.getName() + "'s turn");
	}

	private boolean checkWinner(int row, int col) {
		String mark = player1Turn ? "X" : "O";

		// Check row
		if (cells[row][0].getText().equals(mark) && cells[row][1].getText().equals(mark)
				&& cells[row][2].getText().equals(mark)) {
			highlightWinningCells(row, 0, row, 1, row, 2);
			return true;
		}

		// Check column
		if (cells[0][col].getText().equals(mark) && cells[1][col].getText().equals(mark)
				&& cells[2][col].getText().equals(mark)) {
			highlightWinningCells(0, col, 1, col, 2, col);
			return true;
		}

		// Check diagonal
		if (row == col && cells[0][0].getText().equals(mark) && cells[1][1].getText().equals(mark)
				&& cells[2][2].getText().equals(mark)) {
			highlightWinningCells(0, 0, 1, 1, 2, 2);
			return true;
		}

		// Check other diagonal
		if (row + col == 2 && cells[0][2].getText().equals(mark) && cells[1][1].getText().equals(mark)
				&& cells[2][0].getText().equals(mark)) {
			highlightWinningCells(0, 2, 1, 1, 2, 0);
			return true;
		}

		return false;
	}

	private void highlightWinningCells(int r1, int c1, int r2, int c2, int r3, int c3) {
		cells[r1][c1].setBackground(new Color(98, 140, 88));
		cells[r2][c2].setBackground(new Color(98, 140, 88));
		cells[r3][c3].setBackground(new Color(98, 140, 88));
	}
}
