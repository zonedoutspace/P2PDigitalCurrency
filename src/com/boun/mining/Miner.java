package com.boun.mining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.boun.operations.BlockOperations;
import com.boun.server.Properties;
import com.boun.structures.Transaction;

public class Miner extends Thread{

	public ArrayList<Transaction> transactions;
	
	public Miner(HashSet<Transaction> transactions) {
		super();
		this.transactions = (ArrayList)Arrays.asList(transactions.toArray());
	}
	
	@Override
	public void run() {
		
		try {
			BlockOperations.mine(1, transactions, "NO HASH FOR BLOCK 1", Properties.TARGET);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}