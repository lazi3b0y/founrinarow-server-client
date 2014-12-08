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
	int yCord;
	
	public MainFrame(){		
		super("Server-Window");
		yCord = 0;
		
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
		label.setBounds(200, yCord, 200, 30);
		yCord += 50;
		this.add(label);
	}
}