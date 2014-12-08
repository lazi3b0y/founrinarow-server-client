package client;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import com.interf.fourinarow.Constant;

public class GameClient {
    public static void main(String[] args) {
    	//Comment? Yes please mr comment sir work
    	Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", Constant.RMI_PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating client.");
			System.exit(0);
		}
    	System.out.println("Fetched registry from port " + Constant.RMI_PORT);
    	ClientCom clientCom = null;
		try {
			clientCom = new ClientCom();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating client.");
			System.exit(0);
		}
    	System.out.println("ClientCom instansiated.");
    	try {
			registry.rebind(Constant.CLIENTCOM1_ID, clientCom);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.toString(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating client.");
			System.exit(0);
		}
    	System.out.println("ClientCom bound to the ID: " + Constant.CLIENTCOM1_ID + ".");
    }
}