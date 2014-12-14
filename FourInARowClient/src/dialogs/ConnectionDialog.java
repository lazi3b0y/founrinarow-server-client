package dialogs;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ConnectionDialog extends JFrame{
	private String ip;
	private String port;
	private JButton Connect;
	private JLabel ipLabel;
	private JLabel portLabel;
	private TextField ipTextField;
	private TextField portTextField;
	
	public ConnectionDialog(){
		
		portLabel = new JLabel("Enter port number: ");
		portLabel.setBounds(155, 25, 110, 25);
		add(portLabel);
		
		portTextField = new TextField(25);
		portTextField.setBounds(155, 50, 100, 25);
		add(portTextField);
		
		ipLabel = new JLabel("Enter ip adress: ");
		ipLabel.setBounds(50, 25, 110, 25);
		add(ipLabel);
		
		ipTextField = new TextField(25);
		ipTextField.setBounds(50, 50, 100, 25);
		add(ipTextField);
		
		Connect = new JButton("Connect! ");
		Connect.setBounds(100, 100, 100, 25);
		add(Connect);
		
		ConnectionListener();
		setLayout(null);
		setTitle("Hellow World!");
		setSize(300, 180);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void ConnectionListener() {
		
		Connect.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				if(!ipTextField.getText().equals("") && !portTextField.getText().equals("")){
					ip = ipTextField.getText();
					port = portTextField.getText();
					dispose();
				}
			}
		});
	}
	
	public String getIp(){
		return ip;
	}
	public String getPort(){
		return port;
	}
}

