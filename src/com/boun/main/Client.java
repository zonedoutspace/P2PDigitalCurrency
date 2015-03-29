package com.boun.main;

import com.boun.network.peers.PeerOperation;

public class Client {

	public static void main(String[] args) {
		
		System.out.println("Hello the program");
		System.out.println("Downloading peers' ip addresses...");
		
		
		/*NetworkListener listener = new NetworkListener();
		listener.start();*/
		
		
		try {
			PeerOperation.sendIpToServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("gönderilemedi");
		}
		
		System.out.println("The program is finishing.");		

	}

}
