package com.interf.fourinarow;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClientCom extends Remote {
	public void setSetupGameDialogToVisible() throws RemoteException;
    public void setGameBoardToVisible() throws RemoteException;
    public void updateGameBoard(int[][] gameGrid) throws RemoteException;
    public int getGameBoardPlayerInput() throws RemoteException, InterruptedException;
    public void setGameFrameToVisible() throws RemoteException, Exception;
    public void showWinnerDialog(String playerName) throws RemoteException;
    public void showDrawnDialog() throws RemoteException;
    public void showInvalidDialog(String invalid) throws RemoteException;
    public void setPlayer(int idTag) throws RemoteException;
    public void displayWaitDialog() throws RemoteException;
    public void disposeWaitingDialog() throws RemoteException;
}