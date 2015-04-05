package com.boun.main;

import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.network.mining.GetNumberOfGenesisRequest;
import com.boun.operations.BlockOperations;
import com.boun.operations.PeerOperation;

public class Client {

	public static void main(String[] args) {
		
		System.out.println("Welcome");	
		
		System.out.println("IP address is being sent...");
		try {
			PeerOperation.sendIpToServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ip adress cannot be sent.");
			System.exit(1);
		}
		
		System.out.println("Downloading peers' ip addresses...");
		
		try {
			PeerOperation.getAllPeersFromServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Peer address cannot be downloaded.");
			System.exit(1);
		}
		
		System.out.println("Getting the number of genesis block...");
			
		GetNumberOfGenesisRequest genesisRequest = new GetNumberOfGenesisRequest();
		try {
			String result = RequestSender.send(genesisRequest, NetworkData.SERVER_IP);
			BlockOperations.setNumberOfGenesis(Integer.valueOf(result));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: The number of genesis block cannot be downloaded.");
			System.exit(1);
		}			

		System.out.println("Genesis blocks are being downlaoded.");
		
		try {
			BlockOperations.downloadGenesisBlocks();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Genesis blocks cannot be downloaded.");
			System.exit(1);
		}
		
		System.out.println("Sending ip to other peers");
		
		
		
		
	}

}
