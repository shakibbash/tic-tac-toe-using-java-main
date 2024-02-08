package tic_tac_toe;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		String name1 = JOptionPane.showInputDialog(null, "Enter name for Player 1:");
		while (name1.trim().equals("")) {
			name1 = JOptionPane.showInputDialog("Please enter a valid name:");
		}
		Player player1 = new Player(name1.toUpperCase(), "X");
		String name2 = JOptionPane.showInputDialog(null, "Enter name for Player 2:");
		while (name2.trim().equals("")) {
			name2 = JOptionPane.showInputDialog("Please enter a valid name:");
		}
		Player player2 = new Player(name2.toUpperCase(), "O");

		TicTacToeGUI game = new TicTacToeGUI(player1, player2);
	}
}
