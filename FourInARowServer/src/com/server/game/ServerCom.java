package com.server.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.interf.fourinarow.RemoteServerCom;

public class ServerCom extends UnicastRemoteObject implements RemoteServerCom {
	private static final long serialVersionUID = 2039209136402692346L;
	
	private GameGrid gameGrid;
	private Player player;
	
	public ServerCom() throws RemoteException {
		gameGrid = new GameGrid();
		player = null;
	}
	
	public int[][] getGameGrid() {
		return gameGrid.getGrid();
	}
	
	public void resetGameGrid() {
		gameGrid.resetGrid();
	}
	
	public void setPlayerName(String name) {
		if (player == null) {
			try {
				player = new Player();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		player.setPlayerName(name);
	}
	
	public void setPlayerMarker(int marker) {
		player.setPlayerMarker(marker);
	}
}
