package com.server.game;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JLabel;

import com.interf.fourinarow.RemoteClientCom;
import com.server.exceptions.DrawnMoveException;
import com.server.exceptions.InvalidMoveException;
import com.server.exceptions.WinningMoveException;
import com.server.gui.MainFrame;

public class GameManager {
	RemoteClientCom clientCom1, clientCom2;
    private Player player1;
    private Player player2;
    private MainFrame mainFrame;
    private GameGrid gameGrid;
    private int nextPlayer = 1;

    public GameManager(Player player1, Player player2, RemoteClientCom clientCom1, RemoteClientCom clientCom2, MainFrame mainFrame) throws Exception {
    	gameGrid = new GameGrid();
        this.clientCom1 = clientCom1;
        this.clientCom2 = clientCom2;
    	this.player1 = player1;
        this.player2 = player2;
        this.mainFrame = mainFrame;
    }

    public void StartGame() throws RemoteException, InterruptedException, NotBoundException {
        int move = 0;
        while (true) {
            //Requesting move from player.
            if (nextPlayer == 1) {
            	clientCom2.displayWaitDialog();
                move = player1.GetNewMove();
                mainFrame.addLabel(new JLabel("Player 1 choose column " + move));
                clientCom2.disposeWaitDialog();
            } else {
            	clientCom1.displayWaitDialog();
                move = player2.GetNewMove();
                mainFrame.addLabel(new JLabel("Player 2 choose column " + move));
                clientCom1.disposeWaitDialog();
            }
            //Trying to make move at selected column.
            try {
                gameGrid.makeMove(move, nextPlayer);
                //Switching what player to request move from.
            } catch (InvalidMoveException e) {
            	if (nextPlayer == 1)
            		clientCom1.showInvalidDialog(e.getMessage());
            	else 
            		clientCom2.showInvalidDialog(e.getMessage());
            } catch (WinningMoveException e) {
                clientCom1.updateGameBoard(gameGrid.getGrid());
                clientCom2.updateGameBoard(gameGrid.getGrid());
                if (e.getMessage().equals("1")) {
                	clientCom1.showWinnerDialog(player1.getPlayerName());
                	clientCom2.showWinnerDialog(player1.getPlayerName());
                } else {
                	clientCom1.showWinnerDialog(player2.getPlayerName());
                	clientCom2.showWinnerDialog(player2.getPlayerName());
                }
                gameGrid.resetGrid();
                clientCom1.updateGameBoard(gameGrid.getGrid());
                clientCom2.updateGameBoard(gameGrid.getGrid());
            } catch (DrawnMoveException e) {
                clientCom1.updateGameBoard(gameGrid.getGrid());
                clientCom2.updateGameBoard(gameGrid.getGrid());
                clientCom1.showDrawnDialog();
                clientCom2.showDrawnDialog();
                gameGrid.resetGrid();
                clientCom1.updateGameBoard(gameGrid.getGrid());
                clientCom2.updateGameBoard(gameGrid.getGrid());
            }
            nextPlayer = (nextPlayer == 1) ? 2 : 1;
            //Updating board.
            clientCom1.updateGameBoard(gameGrid.getGrid());
            clientCom2.updateGameBoard(gameGrid.getGrid());
        }
    }
}
