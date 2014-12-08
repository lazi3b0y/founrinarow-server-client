package com.server.game;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import javax.swing.JOptionPane;

import com.interf.fourinarow.*;

public class GameServer {
    public static void main(String[] args) {
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
    	System.out.println("Server is running..");
    }
}