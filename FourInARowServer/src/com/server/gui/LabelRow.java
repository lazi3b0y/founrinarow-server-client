package com.server.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LabelRow extends JPanel{
	private String serverString;
	private JLabel serverLable;
	
	public LabelRow (String string){
		setLayout(new BorderLayout());
		
		serverString = string;
		serverLable = new JLabel(serverString, SwingConstants.CENTER);
		add(serverLable);
		
	}
}