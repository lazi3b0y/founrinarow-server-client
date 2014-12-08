package dialogs;

import javax.swing.JOptionPane;

public class MoveDialog {

	public void PaintWinningMoveDialog(String playerName) {
		JOptionPane.showMessageDialog(null, playerName + " has won!", "WinningMoveException", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void PaintDrawnMoveDialog() {
		JOptionPane.showMessageDialog(null, "It's a draw!", "DrawnMoveException", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void PaintInvalidMoveDialog(String invalid) {
		JOptionPane.showMessageDialog(null, "Invalid Move!", "InvalidMoveException", JOptionPane.INFORMATION_MESSAGE);
	}
}