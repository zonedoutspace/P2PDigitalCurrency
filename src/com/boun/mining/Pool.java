package com.boun.mining;

import java.util.HashSet;

import com.boun.operations.TransactionOperations;
import com.boun.structures.Transaction;

public class Pool {
	
	public static HashSet<Transaction> verifiedTransactions = new HashSet<Transaction>();
	
	public static final int MINING_THRESHOLD = 10; 
	
	Miner miner;
	
	public static String addTransaction(Transaction transaction){
		

		try {
			
			boolean isContain = verifiedTransactions.contains(transaction);
			
			if(isContain){
				return "It already exists.";
			}
		
			
			String result = TransactionOperations.checkTransaction(transaction);
			
			
			if(!result.equals("Verified")){
				return result;
			}
			else{
				verifiedTransactions.add(transaction);
				return null;
			}
			
			
			

			
			
			
			/*
			if(verifiedTransactions.size()==MINING_THRESHOLD){
				
				Miner miner = new Miner(verifiedTransactions);
				miner.start();
				
			}
			*/
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return "Transaction check error";
		}
		

		
		

	}
	
	
	
}
