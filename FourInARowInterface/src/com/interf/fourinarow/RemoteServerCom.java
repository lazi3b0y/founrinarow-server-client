package com.interf.fourinarow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServerCom extends Remote {
	public int[][] getGameGrid() throws RemoteException;
	public void resetGameGrid() throws RemoteException;
	public void setPlayerName(String name) throws RemoteException;
	public void setPlayerMarker(int marker) throws RemoteException;
}