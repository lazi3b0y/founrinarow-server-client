package client;

import gui.GameBoard;
import gui.GameFrame;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.interf.fourinarow.RemoteServerCom;

import dialogs.MoveDialog;
import dialogs.SetupGameDialog;

public class ClientCom extends UnicastRemoteObject implements RemoteClientCom {
	private static final long serialVersionUID = 7572721052589916852L;
	
    Registry registry;
    RemoteServerCom serverCom;
	MoveDialog moveDialog;
    GameBoard gameBoard;
    GameFrame gameFrame;
    SetupGameDialog setupGameDialog;
    int idTag;
    
    public ClientCom() throws Exception, RemoteException {
    	registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
		serverCom = (RemoteServerCom) registry.lookup(Constant.SERVERCOM_ID);
		setupGameDialog = new SetupGameDialog(serverCom);
    	moveDialog = null;
    	gameBoard = null;
    	gameFrame = null;
    }
    
    public void setSetupGameDialog() {
    	setupGameDialog.setVisible(true);
    }
    
    public void setGameBoardToVisible() {
    	gameBoard.setVisible(true);
    }
    
    public void updateGameBoard(int[][] gameGrid) {
    	gameBoard.updateBoard(gameGrid);
    }
    
    public int getGameBoardPlayerInput() {
    	return gameBoard.getPlayerInput();
    }
    
    public void setGameFrameToVisible() {
    	gameFrame.setVisible(true);
    }
    
    public void showWinnerDialog(String playerName) {
    	moveDialog.PaintWinningMoveDialog(playerName);
    }
    
    public void showDrawnDialog() {
    	moveDialog.PaintDrawnMoveDialog();
    }
    
    public void showInvalidDialog(String invalid) {
    	moveDialog.PaintInvalidMoveDialog(invalid);
    }
    public RemoteServerCom getServerCom(){
    	return serverCom;
    }
    public void setPlayer(int idTag) {
    	this.idTag = idTag;
    }
}
