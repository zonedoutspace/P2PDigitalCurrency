package com.boun.network.requests;

import com.boun.network.NetworkData;

public class NewPeerEchoRequest extends Request {

	public static final int REQUEST_CODE = 1;
	
	public static String ip;
	
	
		
	public static String getMyIp() {
		return ip;
	}

	public static void setMyIp(String myIp) {
		NewPeerEchoRequest.ip = myIp;
	}

	
	public String handleRequest() {
		NetworkData.AddPeer(ip);
		System.out.println("New Peer is added:"+ip);
		return "1";
	}
	
	

	public int getRequestCode() {
		return REQUEST_CODE;
	}

}
