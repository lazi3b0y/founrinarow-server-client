package com.interf.fourinarow;

public interface RemoteServerCom {
	public int[][] getGameGrid();
	public void resetGameGrid();
	public void setPlayerName(String name);
	public void setPlayerMarker(int marker);
}
