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
	 * @throws Exception 
	 * */
	public static void AddPeerSet(HashSet<String> peers) throws Exception{
		
		String[] peerList = peers.toArray(new String[peers.size()]);
		
		String myIp = NetworkData.getMyIp();
		
		for(int i=0;i<peerList.length;++i){
			
			if(!peerList[i].equals(myIp))
				peersAddresses.add(peerList[i]);
		
		}
		
		
		
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
	
	public static String[] getPeersAsList(){
		
		String[] peerList = peersAddresses.toArray(new String[peersAddresses.size()]);
		return peerList;
		
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
