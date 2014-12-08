package dialogs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
    private Registry registry;

    public SetupGameDialog() throws RemoteException {
    	registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
        playerTextField = new JTextField(20);
        jbtOk = new JButton("OK");
        
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
        public void actionPerformed(ActionEvent e) {
            if (!playerTextField.getText().equals("")) {
            	RemoteServerCom serverCom = null;
				try {
					serverCom = (RemoteServerCom) registry.lookup(Constant.SERVERCOM_ID);
				} catch (AccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                serverCom.setPlayerName(playerTextField.getText());
                serverCom.setPlayerMarker(1);
                dispose();
            }
        }
    }
}