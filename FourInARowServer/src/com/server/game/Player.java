package com.server.game;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;

public class Player {
	private Registry registry;
	private RemoteClientCom clientCom;
	private String name;
	private int marker;

	public Player() throws RemoteException {
		super();
		registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
		try {
			clientCom = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.name = "";
        this.marker = -1;
		// TODO Auto-generated constructor stub
	}

	public int GetNewMove() {
		// TODO Auto-generated method stub
		return clientCom.getGameBoardPlayerInput();
	}

	public void setPlayerMarker(int marker) {
		// TODO Auto-generated method stub
		this.marker = marker;
	}

	public int getPlayerMarker() {
		// TODO Auto-generated method stub
		return this.marker;
	}

	public void setPlayerName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	public String getPlayerName() {
		// TODO Auto-generated method stub
		return this.name;
	}
}