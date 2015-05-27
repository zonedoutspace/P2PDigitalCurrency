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
