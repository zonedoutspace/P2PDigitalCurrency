package com.boun.network.peers;

import com.boun.network.NetworkData;
import com.boun.network.RequestSender;

public class PeerOperation {

	
	public static void sendIpToServer() throws Exception{
		NewPeerEchoRequest request = new NewPeerEchoRequest();
		request.initializeRequest();
		
		RequestSender.send(request, NetworkData.SERVER_IP);
		
	}
	
}
