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

package com.boun.structures;

import java.util.ArrayList;
import java.util.Date;

/**
 * content of a block 
 * It is used to generate hash of the block.
 * */
public class BlockContent {

	//id of the block
	int blockID;
	
	//transactions in block
	ArrayList<Transaction> transactions;
	
	//the hash of the parent block
	String parentBlockHash;
	
	//the public key of the miner
	String minerPublicKey;
	
	//the variable for proof-of-work
	long nonce;
	
	//the mining date
	Date date;

	public int getBlockID() {
		return blockID;
	}

	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}

	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getParentBlockHash() {
		return parentBlockHash;
	}

	public void setParentBlockHash(String parentBlockHash) {
		this.parentBlockHash = parentBlockHash;
	}

	public String getMinerPublicKey() {
		return minerPublicKey;
	}

	public void setMinerPublicKey(String minerPublicKey) {
		this.minerPublicKey = minerPublicKey;
	}

	public long getNonce() {
		return nonce;
	}

	public void setNonce(long nonce) {
		this.nonce = nonce;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
