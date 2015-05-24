package com.boun.main;

import com.boun.file.FileOperations;
import com.boun.network.NetworkData;
import com.boun.network.NetworkListener;
import com.boun.network.RequestSender;
import com.boun.network.gets.GetNumberOfGenesisRequest;
import com.boun.operations.BlockOperations;
import com.boun.operations.PeerOperation;
import com.boun.server.Properties;

public class Client {

	public static NetworkListener listener;
	
	
	public static void main(String[] args) {	
		
		
		
		System.out.println("Welcome");	
		
		System.out.println("File and directories are being created.");
		try {
			FileOperations.setup();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Setup Error");
			System.exit(1);
		}
				
		
		System.out.println("Getting the number of genesis block...");
			
		GetNumberOfGenesisRequest genesisRequest = new GetNumberOfGenesisRequest();
		try {
			String result = RequestSender.send(genesisRequest, NetworkData.SERVER_IP);
			Properties.setNumberOfGenesisBlock(Integer.valueOf(result));
			System.out.println("number of genesis"+result);
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

		
		System.out.println("Downloading peers' ip addresses...");
		
		try {
			PeerOperation.getAllPeersFromServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Peer address cannot be downloaded.");
			System.exit(1);
		}
		
		
		System.out.println("Blocks are being downloaded");		
	
		try{
			BlockOperations.getBlockNumberFromPeers();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Number of blocks cannot be downloaded.");
			System.exit(1);
		}
		
		
		//indir onları
	
		
		


		System.out.println("Network Listener is starting.");
		
		listener = new NetworkListener();
		listener.start();
		
		System.out.println("Network is listening.");
		
		
		
		System.out.println("IP address is being sent to server...");
		try {
			PeerOperation.sendIpToServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ip adress cannot be sent.");
			System.exit(1);
		}
		
		
		
		System.out.println("Sending ip to other peers");
		PeerOperation.sendIpToPeers();
		
		
		
	}

}
