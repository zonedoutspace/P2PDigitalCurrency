package com.boun.structures;

import java.util.ArrayList;
import java.util.Date;


/**
 * the content of a transaction that must be signed.
 * */
public class TransactionContent {
	
	public static final int maximumNumberOfInputs =  100;
	public static final int maximumNumberOfOutputs = 100;  
	
	/**the variable names are short
	 * since I want to make the json objects shorter **/

	//transaction time
	private Date tD;
	//transaction inputs
	private ArrayList<TransactionInput> in;
	//transaction outputs
	private ArrayList<TransactionOutput> ou;
	
	
	public Date getTransationDate() {
		return tD;
	}
	
	public void setTransationDate(Date transationDate) {
		this.tD = transationDate;
	}
	
	public ArrayList<TransactionInput> getInputs() {
		return in;
	}
	
	public void setInputs(ArrayList<TransactionInput> inputs) {
		this.in = inputs;
	}
	
	public ArrayList<TransactionOutput> getOutputs() {
		return ou;
	}
	
	public void setOutputs(ArrayList<TransactionOutput> outputs) {
		this.ou = outputs;
	}	
	
}
