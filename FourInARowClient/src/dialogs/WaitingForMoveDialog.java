package dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WaitingForMoveDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JLabel WFMLabel;
	public WaitingForMoveDialog(){
		
		WFMLabel = new JLabel("Your opponent is making a move.. ");
		WFMLabel.setBounds(90, 15, 200, 20);

		setLayout(null);
		add(WFMLabel);
		setTitle("Waiting for opponent to make a move.. ");
		setSize(355, 80);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}

}
