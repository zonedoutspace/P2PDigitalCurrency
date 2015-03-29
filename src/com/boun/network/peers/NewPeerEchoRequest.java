package com.boun.network.peers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.boun.network.NetworkData;
import com.boun.network.Request;

public class NewPeerEchoRequest extends Request {

	public static final int REQUEST_CODE = 1;
	
	public String ip;
	
	public String handleRequest() {
		NetworkData.AddPeer(ip);
		System.out.println("New Peer is added:"+ip);
		return "1";
	}

	public int getRequestCode() {
		return REQUEST_CODE;
	}

	@Override
	public void initializeRequest() throws Exception {
		ip = NetworkData.getMyIp();
		System.out.println("my ip:"+ip);
	}

}
