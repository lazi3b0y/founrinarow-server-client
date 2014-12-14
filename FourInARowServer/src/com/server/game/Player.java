package com.server.game;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import com.interf.fourinarow.RemoteClientCom;

public class Player {
	private RemoteClientCom clientCom;
	private String name;
	private int marker;

	public Player(Registry registry, RemoteClientCom clientCom) throws RemoteException {
		super();
        name = "";
        marker = 0;
        this.clientCom = clientCom;
	}

	public int GetNewMove() throws RemoteException, InterruptedException {
		while(true) {
			try {
				return clientCom.getGameBoardPlayerInput();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("fan");
				//e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("fitta");
				//e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("hora");
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