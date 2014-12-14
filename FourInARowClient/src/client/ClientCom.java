package client;

import gui.GameBoard;
import gui.GameFrame;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.interf.fourinarow.RemoteServerCom;

import dialogs.MoveDialog;
import dialogs.SetupGameDialog;
import dialogs.WaitingDialog;

public class ClientCom extends UnicastRemoteObject implements RemoteClientCom {
	private static final long serialVersionUID = 7572721052589916852L;
	
    Registry registry;
    RemoteServerCom serverCom;
	MoveDialog moveDialog;
    GameBoard gameBoard;
    GameFrame gameFrame;
    SetupGameDialog setupGameDialog;
    WaitingDialog waitingDialog;
    int idTag;
    
    public ClientCom(Registry registry) throws RemoteException, NotBoundException {
    	this.registry = registry;
		this.serverCom = (RemoteServerCom) registry.lookup(Constant.SERVERCOM_ID);
		setupGameDialog = null;
    	moveDialog = null;
    	gameBoard = null;
    	gameFrame = null;
    	waitingDialog = new WaitingDialog();
    }
    
    public void setSetupGameDialogToVisible() throws RemoteException {
    	if (setupGameDialog == null) {
    		setupGameDialog = new SetupGameDialog(serverCom);
    	}
    	setupGameDialog.setVisible(true);
    }
    
    public void setGameFrameToVisible() throws Exception {
    	if (gameFrame == null) {
    		if (idTag == 1) {
    			gameFrame = new GameFrame("Player 1");
    		} else {
    			gameFrame = new GameFrame("Player 2");
    		}
    	}
    	gameFrame.setVisible(true);
    }
    
    public void setGameBoardToVisible() {
    	if (gameBoard == null) {
    		gameBoard = gameFrame.getGameBoard();
    	}
    	gameBoard.setVisible(true);
    }
    
    public void updateGameBoard(int[][] gameGrid) {
    	gameBoard.updateBoard(gameGrid);
    }
    
    public int getGameBoardPlayerInput() throws InterruptedException {
    	return gameBoard.getPlayerInput();
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
    
    public void setPlayer(int idTag) throws RemoteException {
    	if (setupGameDialog == null) {
    		setupGameDialog = new SetupGameDialog(serverCom);
    	}
    	setupGameDialog.setIdTag(idTag);
    	setupGameDialog.constructSetupGameDialog(idTag);
    }
    
    public void displayWaitDialog() {
    	if (waitingDialog == null) {
    		waitingDialog = new WaitingDialog();
    	}
    	waitingDialog.setVisible(true);
    }
    
    public void disposeWaitDialog() {
    	waitingDialog.disposeDialog();
    	waitingDialog = null;
    }
}