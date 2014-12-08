package com.server.game;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import com.interf.fourinarow.*;

public class GameServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    	Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
    	System.out.println("Registry instansiated.");
    	ServerCom serverCom = new ServerCom();
    	System.out.println("ServerCom instansiated.");
    	registry.bind(Constant.SERVERCOM_ID, serverCom);
    	System.out.println("ServerCom bound to the ID: " + Constant.SERVERCOM_ID);
    	System.out.println("Server is running..");
    }
}