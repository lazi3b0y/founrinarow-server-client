package com.server.game;

import java.rmi.NotBoundException;
import java.rmi.registry.Registry;

import javax.swing.JLabel;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.server.gui.MainFrame;

public class SetUpGame {
	Registry registry;
	ServerCom serverCom;
	MainFrame mainFrame;
	Player player1, player2;
	RemoteClientCom clientCom1;
	RemoteClientCom clientCom2;
	GameManager gameManager;
	
	public SetUpGame(Registry registry, ServerCom serverCom, MainFrame mainFrame) {
		this.registry = registry;
		this.serverCom = serverCom;
		this.mainFrame = mainFrame;
		this.player1 = null;
		this.player2 = null;
		gameManager = null;
		clientCom1 = null;
		clientCom2 = null;
	}
	
	public void waitForPlayers() throws Exception {
    	boolean ready = false;

    	while(ready == false) {
    		ready = true;
    		try {
				clientCom1 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
			} catch (NotBoundException e) {
				//e.printStackTrace();
				ready = false;
				Thread.sleep(500);
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
				Thread.sleep(500);
			}
    	}
    	mainFrame.addLabel(new JLabel("A client connected to the server and is bound to " + Constant.CLIENTCOM2_ID + "."));
    	serverCom.setPlayers(clientCom1, clientCom2);
    	
    	ready = false;
    	while(ready == false) {
    		if (serverCom.getPlayer1() == null && serverCom.getPlayer2() == null) {
    			System.out.println("player 1 = null; player 2 = null");
    			Thread.sleep(500);
    		} else if (serverCom.getPlayer1() != null && serverCom.getPlayer2() == null) {
    			System.out.println("player 2 = null");
    			Thread.sleep(500);
    		} else if (serverCom.getPlayer1() == null && serverCom.getPlayer2() != null) {
    			System.out.println("player 1 = null");
    			Thread.sleep(500);
    		} else {
    			player1 = serverCom.getPlayer1();
    			player2 = serverCom.getPlayer2();
    			mainFrame.addLabel(new JLabel("Initializing Player 1's game gui.."));
    			clientCom1.setGameFrameToVisible();
    			clientCom1.setGameBoardToVisible();
    			mainFrame.addLabel(new JLabel("Initializing Player 2's game gui.."));
    			clientCom2.setGameFrameToVisible();
    			clientCom2.setGameBoardToVisible();
    			ready = true;
    		}
    	}
    	gameManager = new GameManager(player1, player2, clientCom1, clientCom2, mainFrame);
    	gameManager.StartGame();
	}
}
