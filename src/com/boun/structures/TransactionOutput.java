package com.boun.structures;

/**
 * outputs of a transaction
 **/
public class TransactionOutput {

	/**the variable names are short
	 * since I want to make the json objects shorter**/
	
	//the receiver's public Key
	String rPK;
	//the amount of the output
	int a;
	
	
	public String getReceiverPublicKey() {
		return rPK;
	}
	
	public void setReceiverPublicKey(String receiverPublicKey) {
		this.rPK = receiverPublicKey;
	}
	
	public int getAmount() {
		return a;
	}
	
	public void setAmount(int amount) {
		this.a = amount;
	}
	
}
