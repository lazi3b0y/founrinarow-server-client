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
		yCord = 5;
		
		terminate = new JButton("TERMINATE");
		terminate.setBounds(200, 600, 100, 50);
		this.add(terminate);
		
		this.setLayout(null);
		this.setSize(500,700);
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
	public void addLabel(JLabel label){
		label.setBounds(5, yCord, 490, 15);
		this.add(label);
		this.revalidate();
		this.repaint();
		yCord += 15;
	}
}