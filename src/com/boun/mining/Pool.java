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
