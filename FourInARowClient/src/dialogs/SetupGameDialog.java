package dialogs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteServerCom;

public class SetupGameDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
    private JTextField playerTextField;
    private JButton jbtOk;
    private GridLayout grid = new GridLayout(0, 1);
    private RemoteServerCom serverCom;
    private String id;

    public SetupGameDialog(RemoteServerCom serverCom, String id) throws RemoteException {
        playerTextField = new JTextField(20);
        jbtOk = new JButton("OK");
        this.id = id;
        
        setModal(true);
        jbtOk.addActionListener(new SaveNamesListener());
        setLayout(grid);
        getRootPane().setDefaultButton(jbtOk);
        setLocationRelativeTo(null);
        add(new JLabel("Player 1:"));
        add(playerTextField);
        add(jbtOk, BorderLayout.PAGE_END);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setVisible(false);
        setResizable(false);
    }

    private class SaveNamesListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (!playerTextField.getText().equals("")) {
				System.out.println("Setting player name and marker.");
                try {
					serverCom.setPlayerName(playerTextField.getText());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
                	if (id == Constant.CLIENTCOM1_ID) {
                		serverCom.setPlayerMarker(1);
                	} else {
                		serverCom.setPlayerMarker(2);
                	}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
            }
        }
    }
}