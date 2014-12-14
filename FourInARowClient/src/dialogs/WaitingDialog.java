package dialogs;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class WaitingDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int DO_NOTHING_ON_CLOSE = 0;
	JDialog WD;
	JLabel WaitingMsg = new JLabel("Waiting for opponent to join the server! ");
	public WaitingDialog(){
		//super(frame, "Waiting for opponent to join the server");
		
		JLabel WDLabel = new JLabel("Waiting for a second player! ");
		WDLabel.setBounds(90, 15, 200, 20);
		
		setLayout(null);
		add(WDLabel);
		setSize(355, 80);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
	
	public void disposeDialog() {
		dispose();
	}
}
