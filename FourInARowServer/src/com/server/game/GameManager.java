package com.server.game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.interf.fourinarow.Constant;
import com.interf.fourinarow.RemoteClientCom;
import com.server.exceptions.DrawnMoveException;
import com.server.exceptions.InvalidMoveException;
import com.server.exceptions.WinningMoveException;

public class GameManager {
	Registry registry;
	RemoteClientCom clientCom1;
    private Player player1;
    private Player player2;
    private GameGrid gameGrid;
    private int nextPlayer = 1;

    public GameManager(Player p1, Player p2) throws Exception {
        registry = LocateRegistry.getRegistry("localhost", Constant.RMI_PORT);
        clientCom1 = (RemoteClientCom) registry.lookup(Constant.CLIENTCOM1_ID);
    	player1 = p1;
        player2 = p2;
        gameGrid = new GameGrid();
    }

    public void StartGame() throws RemoteException {
        int move = 0;
        while (true) {
            //Requesting move from player.
            if (nextPlayer == 1)
                move = player1.GetNewMove();
            else
                move = player2.GetNewMove();
            //Trying to make move at selected column.
            try {
                gameGrid.makeMove(move, nextPlayer);
                //Switching what player to request move from.
                nextPlayer = (nextPlayer == 1) ? 2 : 1;
            } catch (InvalidMoveException e) {
            	clientCom1.showInvalidDialog(e.getMessage());
            } catch (WinningMoveException e) {
                clientCom1.updateGameBoard(gameGrid.getGrid());
                if (e.getMessage().equals("1")) {
                	clientCom1.showWinnerDialog(player1.getPlayerName());
                } else {
                	clientCom1.showWinnerDialog(player2.getPlayerName());
                }
                gameGrid.resetGrid();
                clientCom1.updateGameBoard(gameGrid.getGrid());
            } catch (DrawnMoveException e) {
                clientCom1.updateGameBoard(gameGrid.getGrid());
                clientCom1.showDrawnDialog();
                gameGrid.resetGrid();
                clientCom1.updateGameBoard(gameGrid.getGrid());
            }
            //Updating board.
            clientCom1.updateGameBoard(gameGrid.getGrid());
        }
    }
}
