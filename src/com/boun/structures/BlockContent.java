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
	int nonce;
	
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

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
