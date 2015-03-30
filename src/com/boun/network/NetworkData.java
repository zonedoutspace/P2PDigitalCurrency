package com.boun.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

public class NetworkData {
	
	public static final int APP_PORT = 9001;
	
	public static final String SERVER_IP = "104.131.24.193";
	
	public static class PeerList{
		public HashSet<String> peerSet = new HashSet<String>();
	}
	
	private static HashSet<String> peersAddresses = new HashSet<String>();	
	
	
	public static void AddPeer(String newPeerIp){
		peersAddresses.add(newPeerIp);
	}
	
	public static void AddPeerSet(HashSet<String> peers){
		peersAddresses.addAll(peers);
	}

	public static String getMyIp() throws Exception{
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		return in.readLine(); //you get the IP as a String
		
	}
	
	public static PeerList getPeerList(){
		PeerList peerList = new PeerList();
		peerList.peerSet = peersAddresses;
		return peerList;
	}
	
}
