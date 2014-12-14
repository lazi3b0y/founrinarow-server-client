package com.server.game;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;

public class Player {
	private RemoteClientCom clientCom;
	private Registry registry;
	private String name;
	private int marker;

	public Player(Registry registry, RemoteClientCom clientCom) throws RemoteException {
		super();
        name = "";
        marker = 0;
        this.clientCom = clientCom;
        this.registry = registry;
	}

	public int GetNewMove() throws RemoteException, InterruptedException, NotBoundException {
		if (clientCom == null) {
			if (marker == 1)
				clientCom = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
			else
				clientCom = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM2_ID);
		}
		while(true) {
			try {
				int move = clientCom.getGameBoardPlayerInput();
				System.out.println(move);
				return move;
			} catch (NullPointerException e) {
				Thread.sleep(500);
				//e.printStackTrace();
			}
		}
	}

	public void setPlayerMarker(int marker) {
		this.marker = marker;
	}

	public int getPlayerMarker() {
		return marker;
	}

	public void setPlayerName(String name) {
		this.name = name;
	}
	
	public String getPlayerName() {
		return name;
	}
}