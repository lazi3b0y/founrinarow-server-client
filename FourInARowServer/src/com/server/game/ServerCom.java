package com.server.game;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JLabel;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.interf.fourinarow.RemoteServerCom;
import com.server.gui.MainFrame;

public class ServerCom extends UnicastRemoteObject implements RemoteServerCom {
	private static final long serialVersionUID = 2039209136402692346L;
	
	private GameGrid gameGrid;
	private Player player1, player2;
	private Registry registry;
	private RemoteClientCom clientCom1;
	private RemoteClientCom clientCom2;
	private MainFrame mainFrame;
	
	public ServerCom(MainFrame mainFrame, Registry registry) throws RemoteException, NotBoundException {
		gameGrid = new GameGrid();
		player1 = null;
		player2 = null;
		clientCom1 = null;
		clientCom2 = null;
		this.registry = registry;
		this.mainFrame = mainFrame;
	}
	
	public int[][] getGameGrid() {
		return gameGrid.getGrid();
	}
	
	public void resetGameGrid() {
		gameGrid.resetGrid();
	}
	
	public void setPlayerName(String name, int idTag) throws RemoteException {
		if (idTag == 1) {
				player1 = new Player();
				player1.setPlayerName(name);
				player1.setPlayerMarker(idTag);
				mainFrame.addLabel(new JLabel("Set Player 1's name to " + name));
		} else {
				player2 = new Player();
				player2.setPlayerName(name);
				player2.setPlayerMarker(idTag);
				mainFrame.addLabel(new JLabel("Set Player 2's name to " + name));
		}
	}
	
	public void setPlayers(RemoteClientCom clientCom1, RemoteClientCom clientCom2) throws AccessException, RemoteException, NotBoundException{
		clientCom1.setPlayer(1);
		clientCom2.setPlayer(2);
		clientCom1.setSetupGameDialogToVisible();
		clientCom2.setSetupGameDialogToVisible();
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
}