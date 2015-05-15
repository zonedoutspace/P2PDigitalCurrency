package com.boun.mining;

import java.util.HashSet;

import com.boun.structures.Transaction;

public class Pool {
	
	public static HashSet<Transaction> verifiedTransactions = new HashSet<Transaction>();
	
	public static final int MINING_THRESHOLD = 10; 
	
	Miner miner;
	
	
	
	
	public static void addTransaction(Transaction transaction){
		verifiedTransactions.add(transaction);
		
		if(verifiedTransactions.size()==MINING_THRESHOLD){
			
			Miner miner = new Miner(verifiedTransactions);
			miner.start();
			
		}
	}
	
	
	
}
