package com.boun.network.forwards;

import com.boun.mining.Pool;
import com.boun.network.NetworkData;
import com.boun.network.Request;
import com.boun.structures.Transaction;

public class TransactionForwardRequest extends Request{

	
	public static final int REQUEST_CODE = 6;
	
	public Transaction transaction;
	public String senderIp;
	
	
	@Override
	public int getRequestCode() {
		return REQUEST_CODE;
	}

	@Override
	public void initializeRequest() throws Exception {
		senderIp = NetworkData.getMyIp();
		System.out.println("my ip:"+senderIp);
		
	}

	@Override
	public String handleRequest() {
		System.out.println("handleRequest@TransactionForwardRequest");
		
		try {
			String s =  Pool.addTransaction(transaction);
			if(s!=null){
				System.out.println("transaction forward result:"+s);
				return s;
			}
			else{
				
				TransactionForwardThread thread = 
						new TransactionForwardThread(transaction, senderIp);
				
				thread.start();
				System.out.println("transaction forward result:"+"Transaction is added.");
				return "Transaction is added.";
			}
		} catch (Exception e) {
			return "Exception:"+e.getMessage();
		}
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
	
	
	
	
}
