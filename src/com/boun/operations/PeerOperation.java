/**
 	This file is part of Simple Peer to Peer Digital Currency Project.

    It is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    It is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with it.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.boun.operations;

import java.util.HashSet;

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
	
	public static void sendIpToPeers(){
		
		HashSet<String> peersAddresses = NetworkData.getPeerList().peerSet;
		
		String[] peerList = peersAddresses.toArray(new String[peersAddresses.size()]);
		
		for(int i=0;i<peerList.length;++i){
			
			
			try{
				NewPeerEchoRequest request = new NewPeerEchoRequest();
				request.initializeRequest();
				
				System.out.println("Ip is sending to *"+ peerList[i]+"*");
				RequestSender.send(request, peerList[i]);
				System.out.println("Ip was sent to"+ peerList[i]);
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Ip wasn't sent to" + peerList[i]);
			}
			
		}
		
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
