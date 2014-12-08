package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.interf.fourinarow.Constant;

public class GameClient {
    public static void main(String[] args) throws Exception {
    	//Comment? Yes please mr comment sir work
    	Registry registry = LocateRegistry.getRegistry("127.0.0.1", Constant.RMI_PORT);
    	System.out.println("Fetched registry from port " + Constant.RMI_PORT);
    	ClientCom clientCom = new ClientCom();
    	System.out.println("ClientCom instansiated.");
    	registry.bind(Constant.CLIENTCOM_ID, clientCom);
    	System.out.println("ClientCom bound to the ID: " + Constant.CLIENTCOM_ID);
    }
}