package com.server.game;

import com.interf.fourinarow.Constant;
import com.server.exceptions.DrawnMoveException;
import com.server.exceptions.InvalidMoveException;
import com.server.exceptions.WinningMoveException;

public class GameGrid {
	private int[][] grid = new int[Constant.ROWS][Constant.COLUMNS];
    
    public GameGrid() {
        //Initializing.
        for (int i = 0; i < Constant.ROWS; i++)
            for (int j = 0; j < Constant.COLUMNS; j++)
                grid[i][j] = 0;
    }

    //Getter for the grid.
    public int[][] getGrid() {
        return this.grid;
    }

    public void resetGrid() {
        for (int i = 0; i < Constant.ROWS; i++)
            for (int j = 0; j < Constant.COLUMNS; j++)
                grid[i][j] = 0;
    }

    public void makeMove(int column, int marker) throws InvalidMoveException, WinningMoveException, DrawnMoveException {
        //Check if the column is already "full".
        if (grid[0][column] == 0)
            placeMarker(column, marker);
        else
            throw new InvalidMoveException();
        //Checking if this move made the player a winner.
        if (checkForWinner(getLastColumnMove(column), column, marker))
            throw new WinningMoveException("" + marker);
        if (checkForDrawn())
            throw new DrawnMoveException();
    }

    public boolean checkForWinner(int row, int column, int marker) {
        boolean won = false;
        if ((checkLeft(row, column, marker) + 1 + checkRight(row, column, marker) >= 4) ||
            (checkLeftUp(row, column, marker) + 1 + checkRightDown(row, column, marker) >= 4) ||
            (checkLeftDown(row, column, marker) + 1 + checkRightUp(row, column, marker) >= 4) ||
            (checkDown(row, column, marker) + 1 >= 4))
            won = true;
        return won;
    }

    public int checkRightUp(int row, int column, int marker) {
        row--;
        column++;
        if (row >= 0 && column < Constant.COLUMNS && grid[row][column] == marker)
            return 1 + checkRightUp(row, column, marker);
        return 0;
    }

    public int checkRightDown(int row, int column, int marker) {
        row++;
        column++;
        if (row < Constant.ROWS && column < Constant.COLUMNS && grid[row][column] == marker)
            return 1 + checkRightDown(row, column, marker);
        return 0;
    }

    public int checkLeftDown(int row, int column, int marker) {
        row++;
        column--;
        if (row < Constant.ROWS && column >= 0 && grid[row][column] == marker)
            return 1 + checkLeftDown(row, column, marker);
        return 0;
    }

    public int checkLeftUp(int row, int column, int marker) {
        row--;
        column--;
        if (row >= 0 && column >= 0 && grid[row][column] == marker)
            return 1 + checkLeftUp(row, column, marker);
        return 0;
    }

    public int checkLeft(int row, int column, int marker) {
        column--;
        if (column >= 0 && grid[row][column] == marker)
            return 1 + checkLeft(row, column, marker);
        return 0;
    }

    public int checkRight(int row, int column, int marker) {
        column++;
        if (column < Constant.COLUMNS && grid[row][column] == marker)
            return 1 + checkRight(row, column, marker);
        return 0;
    }

    public int checkDown(int row, int column, int marker) {
        row++;
        if (row < Constant.ROWS && grid[row][column] == marker)
            return 1 + checkDown(row, column, marker);
        return 0;
    }

    public int getLastColumnMove(int column) {
        for (int i = 0; i < Constant.ROWS; i++)
            if (grid[i][column] != 0)
                return i;
        return Constant.ROWS;
    }

    public void placeMarker(int column, int marker) {
        grid[getLastColumnMove(column) - 1][column] = marker;
    }

    public boolean checkForDrawn() {
        for (int i = 0; i < Constant.COLUMNS; i++)
            if (grid[0][i] == 0)
                return false;
        return true;
    }
}
