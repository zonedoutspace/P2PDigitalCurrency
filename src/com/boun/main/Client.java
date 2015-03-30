package com.boun.main;

import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.network.mining.GetNumberOfGenesisRequest;
import com.boun.network.mining.GetTargetRequest;
import com.boun.network.peers.PeerOperation;

public class Client {

	public static void main(String[] args) {
		
		System.out.println("Hello the program");
		System.out.println("Downloading peers' ip addresses...");
		
		
		/*NetworkListener listener = new NetworkListener();
		listener.start();*/
		
		/*
		try {
			PeerOperation.sendIpToServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("gönderilemedi");
		}
		
		try {
			PeerOperation.getAllPeersFromServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*GetNumberOfGenesisRequest genesisRequest = new GetNumberOfGenesisRequest();
		try {
			String result = RequestSender.send(genesisRequest, NetworkData.SERVER_IP);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		GetTargetRequest request = new GetTargetRequest();
		try {
			System.out.println(RequestSender.send(request, NetworkData.SERVER_IP));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				

	}

}
