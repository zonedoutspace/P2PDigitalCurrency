package com.boun.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

public class NetworkData {
	
	//The application port
	public static final int APP_PORT = 9001;
	
	//The server ip of the network
	public static final String SERVER_IP = "104.43.253.52";
	
	//the ip list of the peers
	private static HashSet<String> peersAddresses = new HashSet<String>();	
	
	/**
	 * adds a new peer IP to the memory
	 * */
	public static void AddPeer(String newPeerIp){
		peersAddresses.add(newPeerIp);
	}
	
	/**
	 * adds multiple peers' IP addresses to the memory
	 * */
	public static void AddPeerSet(HashSet<String> peers){
		peersAddresses.addAll(peers);
	}

	
	/**
	 * gets IP address from network
	 * */
	public static String getMyIp() throws Exception{
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		return in.readLine();
		
	}
	
	/**
	 * creates peer lists as PeerList class
	 * */
	public static PeerList getPeerList(){
		PeerList peerList = new PeerList();
		peerList.peerSet = peersAddresses;
		return peerList;
	}
	
	/**
	 * auxiliary class for simple json usage 
	 * */
	public static class PeerList{
		public HashSet<String> peerSet = new HashSet<String>();
	}
	
}
