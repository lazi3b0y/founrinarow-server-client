package com.server.gui;

import java.awt.*;

import javax.swing.JPanel;

public class ServerLabels extends JPanel {
	private String serverString;
	private LabelRow[] labels;
	private int xCord, yCord;
	
	public ServerLabels (String string){
		setLayout(null);
		
		serverString = string;
		labels = new LabelRow[5];
		xCord = 0;
		yCord = 0;
		
		for(int i=0; i < labels.length; i++){
			labels[i] = new LabelRow(serverString);
			labels[i].setBounds(xCord, yCord, 100, 30);
			yCord += 50;
			add(labels[i]);
			
		}
	}
}