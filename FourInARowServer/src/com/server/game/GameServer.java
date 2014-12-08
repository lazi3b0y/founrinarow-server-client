package com.server.game;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.interf.fourinarow.Constant;
import com.server.gui.MainFrame;

public class GameServer {
    public static void main(String[] args) {
    	MainFrame mainFrame = new MainFrame();
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
    	mainFrame.addLabel(new JLabel("Registry instansiated."));
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
    	mainFrame.addLabel(new JLabel("ServerCom instansiated"));
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
    	mainFrame.addLabel(new JLabel("ServerCom bound to the ID: " + Constant.SERVERCOM_ID + "."));
    	System.out.println("Server is running...");
    	mainFrame.addLabel(new JLabel("Server is running..."));
    }
}