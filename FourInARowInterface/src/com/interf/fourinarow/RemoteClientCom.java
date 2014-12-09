package com.interf.fourinarow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientCom extends Remote {
	public void setSetupGameDialog() throws RemoteException;
    public void setGameBoardToVisible() throws RemoteException;
    public void updateGameBoard(int[][] gameGrid) throws RemoteException;
    public int getGameBoardPlayerInput() throws RemoteException;
    public void setGameFrameToVisible() throws RemoteException;
    public void showWinnerDialog(String playerName) throws RemoteException;
    public void showDrawnDialog() throws RemoteException;
    public void showInvalidDialog(String invalid) throws RemoteException;
    public void setPlayer(int idTag) throws RemoteException;
}