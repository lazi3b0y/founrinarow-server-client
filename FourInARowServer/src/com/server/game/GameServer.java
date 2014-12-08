package com.server.game;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.interf.fourinarow.Constant;
import com.server.gui.LabelRow;
import com.server.gui.MainFrame;

public class GameServer {
    public static void main(String[] args) {
    	MainFrame mainFrame = new MainFrame();
    	LabelRow servLabel1, servLabel2, servLabel3, servLabel4, servLabel5;
    	int yCord = 0;
    	Registry registry = null;
		try {
			registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
    	System.out.println("Registry instansiated.");
    	servLabel1 = new LabelRow("Registry instansiated", yCord);
    	yCord += 30;
    	mainFrame.addLabel(servLabel1);
    	ServerCom serverCom = null;
		try {
			serverCom = new ServerCom();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
    	System.out.println("ServerCom instansiated.");
    	servLabel2 = new LabelRow("ServerCom instansiated", yCord);
    	yCord += 30;
    	mainFrame.addLabel(servLabel2);
    	try {
			registry.bind(Constant.SERVERCOM_ID, serverCom);
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception Error.",JOptionPane.INFORMATION_MESSAGE);
			System.out.println("Exception encountered. Terminating server.");
			System.exit(0);
		}
    	System.out.println("ServerCom bound to the ID: " + Constant.SERVERCOM_ID + ".");
    	servLabel3 = new LabelRow("ServerCom bound to the ID: " + Constant.SERVERCOM_ID + ".", yCord);
    	yCord += 30;
    	mainFrame.addLabel(servLabel3);
    	System.out.println("Server is running..");
    	servLabel4 = new LabelRow("Server is running..", yCord);
    	yCord += 30;
    	mainFrame.addLabel(servLabel4);
    }
}