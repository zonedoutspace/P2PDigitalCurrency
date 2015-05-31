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

package com.boun.network.forwards;

import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.structures.Transaction;

public class TransactionForwardThread extends Thread{

	Transaction transaction;
	String senderIp;

	public TransactionForwardThread(Transaction transaction,String senderIp) {
		super();
		this.transaction = transaction;
		this.senderIp = senderIp;
	}
	
	@Override
	public void run() {
		
		
		String[] peerList = NetworkData.getPeersAsList();
		
		for(int i=0;i<peerList.length;++i){
			String peer = peerList[i];
			
			if(!peer.equals(senderIp)){
				
				
				System.out.println("forwarding to "+ peer);
				
				TransactionForwardRequest request = new TransactionForwardRequest();
				request.setTransaction(transaction);
				
				try {
					request.initializeRequest();
					RequestSender.send(request, peer);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		}
		
		
		
		
	}
	
	
	
	
}
