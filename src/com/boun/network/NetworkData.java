package com.boun.network;

import java.util.ArrayList;

public class NetworkData {
	
	private static ArrayList<String> peersAddresses = new ArrayList<String>();	
	
	
	public static void AddPeer(String newPeerIp){
		peersAddresses.add(newPeerIp);
	}

	
}
