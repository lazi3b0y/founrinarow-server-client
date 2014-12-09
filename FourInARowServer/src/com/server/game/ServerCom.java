package com.server.game;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.interf.fourinarow.RemoteServerCom;

public class ServerCom extends UnicastRemoteObject implements RemoteServerCom {
	private static final long serialVersionUID = 2039209136402692346L;
	
	private GameGrid gameGrid;
	private Player player1, player2;
	private Registry registry;
	private RemoteClientCom clientCom1;
	private RemoteClientCom clientCom2;
	
	public ServerCom() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry(Constant.RMI_IP, Constant.RMI_PORT);
		clientCom1 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
		clientCom2 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM2_ID);
		gameGrid = new GameGrid();
		player1 = null;
		clientCom1.setPlayer(1);
		clientCom2.setPlayer(2);
	}
	
	public int[][] getGameGrid() {
		return gameGrid.getGrid();
	}
	
	public void resetGameGrid() {
		gameGrid.resetGrid();
	}
	
	public void setPlayerName(String name, int idTag) throws RemoteException {
		if (player1 == null && idTag == 1) {
			try {
				player1 = new Player();
				player1.setPlayerName(name);
				player1.setPlayerMarker(idTag);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
				player2 = new Player();
				player2.setPlayerName(name);
				player2.setPlayerMarker(idTag);
		}
	}
	
	public void setPlayers() throws AccessException, RemoteException, NotBoundException{
		clientCom1 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
		clientCom2 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM2_ID);
		clientCom1.setPlayer(1);
		clientCom2.setPlayer(2);
		clientCom1.setSetupGameDialog();
		clientCom2.setSetupGameDialog();
	}
}