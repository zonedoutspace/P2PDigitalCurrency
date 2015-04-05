package com.boun.operations;

import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.network.NetworkData.PeerList;
import com.boun.network.peers.GetPeersRequest;
import com.boun.network.peers.NewPeerEchoRequest;
import com.google.gson.Gson;

public class PeerOperation {

	
	public static void sendIpToServer() throws Exception{
		NewPeerEchoRequest request = new NewPeerEchoRequest();
		request.initializeRequest();
		
		RequestSender.send(request, NetworkData.SERVER_IP);
		
	}
	
	
	public static void getAllPeersFromServer() throws Exception{
		GetPeersRequest request = new GetPeersRequest();
		
		String result = RequestSender.send(request, NetworkData.SERVER_IP);
		System.out.println("result:"+result);
		Gson gson = new Gson();
		
		PeerList peerList = gson.fromJson(result, PeerList.class);
		
		NetworkData.AddPeerSet(peerList.peerSet);
		
	}
	
}
