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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.boun.operations.BlockOperations;
import com.boun.structures.Block;
import com.boun.structures.Transaction;

public class Miner extends Thread{

	ArrayList<Transaction> transactions;
	int blockId;
	String parentHash;
	String target;
	MinerResultListener listener;
	
	public Miner(HashSet<Transaction> transactions) {
		super();
		this.transactions = (ArrayList)Arrays.asList(transactions.toArray());
	}
	
	@Override
	public void run() {
		
		try {
			Block block = BlockOperations.mine(blockId, transactions, parentHash, target);
			listener.getResult(block);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			listener.getResult(null);
		}
		
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setListener(MinerResultListener listener) {
		this.listener = listener;
	}
	
	
	
}