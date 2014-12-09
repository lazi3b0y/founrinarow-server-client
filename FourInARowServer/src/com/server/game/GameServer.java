package com.server.game;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.interf.fourinarow.Constant;
import com.server.gui.MainFrame;

public class GameServer {
    public static void main(String[] args) throws NotBoundException {
    	MainFrame mainFrame = new MainFrame();
    	Registry registry = null;
    	ServerCom serverCom = null;
    	
		try {
			registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
		
    	System.out.println("Registry created.");
    	mainFrame.addLabel(new JLabel("Registry created."));
    	
		try {
			serverCom = new ServerCom();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
		
    	System.out.println("ServerCom created.");
    	mainFrame.addLabel(new JLabel("ServerCom created"));
    	
    	try {
			registry.bind(Constant.SERVERCOM_ID, serverCom);
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
    	
    	System.out.println("ServerCom bound to ID: " + Constant.SERVERCOM_ID + ".");
    	mainFrame.addLabel(new JLabel("ServerCom bound to ID: " + Constant.SERVERCOM_ID + "."));
    	System.out.println("Server is running...");
    	mainFrame.addLabel(new JLabel("Server is running..."));
    	
    	try {
			waitForPlayers(mainFrame);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void waitForPlayers(MainFrame mainFrame) throws AccessException, RemoteException {
    	boolean ready = false;
    	Registry registry = null;
    	try {
			registry = LocateRegistry.getRegistry("127.0.0.1", Constant.RMI_PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(ready == false) {
    		ready = true;
    		try {
				registry.lookup(Constant.CLIENTCOM1_ID);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ready = false;
			}
    	}
    	
    	mainFrame.addLabel(new JLabel("A client connected to the server and is bound to " + Constant.CLIENTCOM1_ID));
    	
    	ready = false;
    	while (ready == false) {
    		ready = true;
    		try {
				registry.lookup(Constant.CLIENTCOM2_ID);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				ready = false;
			}
    	}
    	mainFrame.addLabel(new JLabel("A client connected to the server and is bound to " + Constant.CLIENTCOM2_ID));
    }
}