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
    private int idTag;

    public SetupGameDialog(RemoteServerCom serverCom) throws RemoteException {
        this.serverCom = serverCom;
    }

    private class SaveNamesListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (!playerTextField.getText().equals("")) {
				System.out.println("Setting player name and marker.");
                try {
					serverCom.setPlayerName(playerTextField.getText(), idTag);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                dispose();
            }
        }
    }
    public void setIdTag(int idTag) {
    	this.idTag = idTag;
    }
    
    public void constructSetupGameDialog(int idTag){
        add(new JLabel("Player " + idTag + ":"));
        
        playerTextField = new JTextField(20);
        add(playerTextField);
        
        getRootPane().setDefaultButton(jbtOk);
        jbtOk = new JButton("OK");
        jbtOk.addActionListener(new SaveNamesListener());
        add(jbtOk, BorderLayout.PAGE_END);
        
        setLayout(grid);
        setSize(300, 200);
        setVisible(false);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}