package com.server.game;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JLabel;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.server.gui.MainFrame;

public class SetUpGame {
	Registry registry;
	ServerCom serverCom;
	MainFrame mainFrame;
	Player player1;
	Player player2;
	RemoteClientCom clientCom1;
	RemoteClientCom clientCom2;
	
	public SetUpGame(Registry registry, ServerCom serverCom, MainFrame mainFrame, Player player1, Player player2) throws RemoteException {
		this.registry = registry;
		this.serverCom = serverCom;
		this.mainFrame = mainFrame;
		this.player1 = player1;
		this.player2 = player2;
		clientCom1 = null;
		clientCom2 = null;
	}
	
	public void waitForPlayers() throws AccessException, RemoteException, NotBoundException {
    	boolean ready = false;

    	while(ready == false) {
    		ready = true;
    		try {
				clientCom1 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
			} catch (NotBoundException e) {
				//e.printStackTrace();
				ready = false;
			}
    	}
    	
    	mainFrame.addLabel(new JLabel("A client connected to the server and is bound to " + Constant.CLIENTCOM1_ID + "."));
    	
    	ready = false;
    	while (ready == false) {
    		ready = true;
    		try {
    			clientCom2 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM2_ID);
			} catch (NotBoundException e) {
				//e.printStackTrace();
				ready = false;
			}
    	}
    	mainFrame.addLabel(new JLabel("A client connected to the server and is bound to " + Constant.CLIENTCOM2_ID + "."));
    	serverCom.setPlayers(clientCom1, clientCom2);
    	
    	ready = false;
    	while(ready == false) {
    		if (player1 == null && player2 == null) {
    			
    		} else if (player1 != null && player2 == null) {
    			
    		} else if (player1 == null && player2 != null) {
    			
    		} else {
    			
    		}
    	}
	}
}
