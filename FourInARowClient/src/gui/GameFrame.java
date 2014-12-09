package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteServerCom;

public class GameFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
    private JPanel contentPane = new JPanel(new BorderLayout());
    private JPanel buttons = new JPanel(new BorderLayout());
    private JButton newGameButton = new JButton("New Game");
    private JButton quitButton = new JButton("Quit");
    private Color color = new Color(255, 51, 51);
    private GameBoard gameBoard = new GameBoard();
    private Registry registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
    private RemoteServerCom serverCom;
    
    public GameFrame() throws Exception {
    	try {
			serverCom = (RemoteServerCom) registry.lookup(Constant.SERVERCOM_ID);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setSize(500, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttons.add(newGameButton, BorderLayout.WEST);
        buttons.add(quitButton, BorderLayout.EAST);
        newGameButton.addActionListener(this);
        quitButton.addActionListener(this);

        contentPane.setBackground(color);
        contentPane.add(gameBoard, BorderLayout.CENTER);
        contentPane.add(buttons, BorderLayout.PAGE_END);
        add(contentPane);
    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void actionPerformed(ActionEvent e) {
	    String buttonName;
	
	    JButton button = (JButton) e.getSource();
	    buttonName = button.getText();
	    if (buttonName.equals("New Game")) {
	        try {
				serverCom.resetGameGrid();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        gameBoard.resetGameBoard();
	    } else {
	        System.exit(0);
	    }
    }
}
