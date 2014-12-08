package com.server.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LabelRow extends JPanel{
	private String serverString;
	private JLabel serverLable;
	
	public LabelRow (String string){
		setLayout(null);
		
		serverString = string;
		serverLable = new JLabel(serverString, SwingConstants.CENTER);
		serverLable.setBounds(200, 0, 100, 30);
		add(serverLable);
		
	}
}