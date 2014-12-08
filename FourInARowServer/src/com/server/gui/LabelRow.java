package com.server.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LabelRow extends JPanel{
	private String serverString;
	private JLabel serverLable;
	private int yCord;
	
	public LabelRow (String string, int y){
		setLayout(null);
		
		yCord = y;
		serverString = string;
		serverLable = new JLabel(serverString, SwingConstants.CENTER);
		serverLable.setBounds(200, yCord, 100, 30);
		add(serverLable);
		
	}
}