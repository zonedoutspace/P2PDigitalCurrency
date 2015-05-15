package com.boun.structures;

import com.google.gson.Gson;

public class Transaction {
	
	/**the variable names are short
	 * since I want to make the json objects shorter**/

	//the sender's public key
	private String sPK;
	//the sign of the transaction
	private String sign;
	//the content that has to be signed.
	private TransactionContent c;
	
	
	public String getSenderPublicKey() {
		return sPK;
	}


	public void setSenderPublicKey(String senderPublicKey) {
		this.sPK = senderPublicKey;
	}


	public String getSign() {
		return sign;
	}


	public void setSign(String sign) {
		this.sign = sign;
	}

	public TransactionContent getContent() {
		return c;
	}

	public void setContent(TransactionContent content) {
		this.c = content;
	}
	
	@Override
	public boolean equals(Object obj) {
		Gson gson = new Gson();		
		return gson.toJson(this).equals(gson.toJson(obj));
	}
	
	@Override
	public int hashCode() {
		Gson gson = new Gson();
		return gson.toJson(this).hashCode();
	}
	
}
