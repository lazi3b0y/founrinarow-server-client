package com.interf.fourinarow;

public interface RemoteClientCom {
    public void setSetupGameDialogToVisible();
    public void setGameBoardToVisible();
    public void updateGameBoard(int[][] gameGrid);
    public int getGameBoardPlayerInput();
    public void setGameFrameToVisible();
    public void showWinnerDialog(String playerName);
    public void showDrawnDialog();
    public void showInvalidDialog(String invalid);
}
