package com.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	/*EN KOMMENTAR TILL FOLKET*/
	JButton terminate;
	JLabel startUpMsg;
	
	public MainFrame(){		
		super("Server-Window");
		
		terminate = new JButton("TERMINATE");
		terminate.setBounds(200, 100, 100, 50);
		this.add(terminate);
		
		this.setLayout(null);
		this.setSize(500,200);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ActionListener();
			
	}
	
	public void ActionListener (){
		
		terminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.out.println("Terminating server.");
				System.exit(0);
			}
		});
	}	
	public void addLabel(LabelRow label){
		this.add(label);
	}
}