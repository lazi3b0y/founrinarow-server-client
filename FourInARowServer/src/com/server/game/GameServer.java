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
    public static void main(String[] args) throws Exception {
    	MainFrame mainFrame = new MainFrame();
    	Registry registry = null;
    	ServerCom serverCom = null;
    	SetUpGame setUpGame = null;
    	
		try {
			registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		} catch (RemoteException e) {
			//e.printStackTrace();
			mainFrame.addLabel(new JLabel(e.getMessage() + "\nException encountered. Terminating server."));
			System.out.println(e.getMessage() + "\nException encountered. Terminating server.");
			System.exit(0);
		}
		
    	System.out.println("Registry created.");
    	mainFrame.addLabel(new JLabel("Registry created."));
    	
		try {
			serverCom = new ServerCom(mainFrame, registry);
		} catch (RemoteException | NotBoundException e) {
			//e.printStackTrace();
			mainFrame.addLabel(new JLabel(e.getMessage() + "\nException encountered. Terminating server."));
			System.out.println(e.getMessage() + "\nException encountered. Terminating server.");
			System.exit(0);
		}
		
    	System.out.println("ServerCom created.");
    	mainFrame.addLabel(new JLabel("ServerCom created."));
    	
    	try {
			registry.bind(Constant.SERVERCOM_ID, serverCom);
		} catch (RemoteException | AlreadyBoundException e) {
			//e.printStackTrace();
			mainFrame.addLabel(new JLabel(e.getMessage() + "\nException encountered. Terminating server."));
			System.out.println(e.getMessage() + "\nException encountered. Terminating server.");
			System.exit(0);
		}
    	
    	System.out.println("ServerCom bound to ID: " + Constant.SERVERCOM_ID + ".");
    	mainFrame.addLabel(new JLabel("ServerCom bound to ID: " + Constant.SERVERCOM_ID + "."));
    	System.out.println("Server is running...");
    	mainFrame.addLabel(new JLabel("Server is running..."));
    	
    	setUpGame = new SetUpGame(registry, serverCom, mainFrame);
		setUpGame.waitForPlayers();
    }
}