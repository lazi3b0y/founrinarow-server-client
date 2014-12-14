package client;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import com.interf.fourinarow.Constant;

import dialogs.SetupGameDialog;

public class GameClient {
    public static void main(String[] args) throws RemoteException, Exception {
    	
    	// Getting the registry, and instansiating clientCom.
    	System.out.println("Fetched registry from " + Constant.RMI_LOCALHOST + ":" + Constant.RMI_PORT);
    	Registry registry = LocateRegistry.getRegistry(Constant.RMI_LOCALHOST, Constant.RMI_PORT);
    	ClientCom clientCom = new ClientCom();
    	System.out.println("clientCom created. Bindning now...");
    	
    	// Trying to bind the class-variable clientCom to a ID in the registry.
    	// If it fails the client terminates.
    	try {
			registry.bind(Constant.CLIENTCOM1_ID, clientCom);
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//JOptionPane.showMessageDialog(null, e.toString() + "\n\n" + Constant.CLIENTCOM1_ID + " is already bound to a different client. Trying to bind you to ClientCom2 instead.", "AlreadyBoundException",JOptionPane.INFORMATION_MESSAGE);
			//System.out.println("AlreadyBoundException encountered. Trying to bind to " + Constant.CLIENTCOM2_ID + ".");
			try {
				registry.bind(Constant.CLIENTCOM2_ID, clientCom);
			} catch (AlreadyBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString() + "\n\nOops, two players are already bound to the current server.", "AlreadyBoundException",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("AlreadyBoundException encountered twice, binding failed. Terminating client.");
				System.exit(0);
			}
		}
    	
    	System.out.println("Binding successful!");
    }
}