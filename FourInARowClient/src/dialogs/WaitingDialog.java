package dialogs;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WaitingDialog{
	private static final long serialVersionUID = 1L;
	private static final int DO_NOTHING_ON_CLOSE = 0;
	JLabel WaitingMsg = new JLabel("Waiting for opponent to join the server! ");
	public WaitingDialog(){
		Frame frame = null;
		
		JLabel WDLabel = new JLabel("Waiting for a second player! ");
		WDLabel.setBounds(90, 15, 200, 20);
		
		JDialog WD = new JDialog(frame, "Waiting for opponent to join the server");
		
		WD.setLayout(null);
		WD.add(WDLabel);
		WD.setSize(355, 80);
		WD.setLocationRelativeTo(null);
		WD.setVisible(true);
		WD.setResizable(false);
		WD.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
	}
}
