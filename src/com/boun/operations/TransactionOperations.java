package com.boun.operations;

import java.util.ArrayList;
import java.util.Date;

import com.boun.signature.Base58;
import com.boun.signature.Generator;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionInput;
import com.boun.structures.TransactionOutput;
import com.google.gson.Gson;


public class TransactionOperations {
	
	public static String checkTransaction(Transaction transaction) throws Exception{
		
		String result = checkTransactionSign(transaction);
		
		if(result!=null){
			return result;
		}
		else{
			
			result = checkValues(transaction);
			
			if(result!=null){
				return result;
			}
			else{
				result = verifyTransactionInputs(transaction);
				
				if(result!=null){
					return result;
				}
				else{
					return "Verified";
				}
				
			}
		}
		
	}

	private static String verifyTransactionInputs(Transaction transaction) {
		
		ArrayList<TransactionInput> inputs = transaction.getContent().getInputs();
		
		String result = null;
		
		for(int i=0;i<inputs.size();++i){
			
			result = checkUnspentInput(inputs.get(i),transaction.getSenderPublicKey());
			
			if(result!=null){
				return result;
			}
			
		}
		
		return result;
	}

	private static String checkUnspentInput(TransactionInput transactionInput,
			String senderPublicKey) {
		
		String result  = null;
		
	
		
		//check ownership and existence
		
		//check unspent
		
		return result;
	}

	private static String checkValues(Transaction transaction) {
		
		TransactionContent content = transaction.getContent();
		
		
		if(content==null){
			return "Transaction Content cannot be empty";
		}
		
		Date date = content.getTransationDate();
		if(date==null){
			return "Date cannot be empty";
		}
		
		
		int inputTotal=0;
		int outputTotal=0;
		
		ArrayList<TransactionInput> inputs =content.getInputs();
		ArrayList<TransactionOutput> outputs = content.getOutputs();
		
		if(inputs==null || inputs.size()<1){
			return "Inputs cannot be empty";
		}
		
		
		//checks inputs and sum them
		for(int i=0;i<inputs.size();++i){
			
			TransactionInput input = inputs.get(i);
			
			if(input.getAmount()<=0){
				return "Error:Amounts must be possitive. input number:"+(i+1);
			}
			else{
				inputTotal+=input.getAmount();
			}
			
			if(input.getOutputOrder()<0){
				return "Error:Output order must be possitive. input number:"+(i+1);
			}
			
			if(input.getTransactionOrder()<0){
				return "Error:Transaction order must be possitive. input number:"+(i+1);
			}
			
		}
		
		if(outputs==null || outputs.size()<1){
			return "Outputs cannot be empty";
		}
		
		//checks outputs and sum them
		for(int i=0;i<outputs.size();++i){
		
			if(outputs.get(i).getAmount()<=0){
				return "Error:Amounts must be possitive. output:"+(i+1);
			}
			
			if(outputs.get(i).getReceiverPublicKey()==null){
				return "Error: Public key cannot be empty. output:"+(i+1);
			}
			else if(outputs.get(i).getReceiverPublicKey().length()<64){
				return "Error: Public key length is short. output:"+(i+1);
			}
			else if(!Base58.checkEncoding(outputs.get(i).getReceiverPublicKey())){
				return "Error: Public key encoding is not valid.  output:"+(i+1);
			}
			
			outputTotal+=outputs.get(i).getAmount();
		}
		
		if(inputTotal!=outputTotal){
			return "the amounts of inputs are not equal to the amount of outputs";
		}
		else{
			return null;
		}
		
		
	}

	private static String checkTransactionSign(Transaction transaction){
		
		Gson gson = new Gson();
		
		boolean result;
		try {
			result = Generator.checkSign(
								gson.toJson(transaction.getContent()),
								transaction.getSign(),
								Generator.getPublicKey(transaction.getSenderPublicKey()));
		} catch (Exception e) {
			return "Signature verification ";
		}
		
		if(result){
			return null;
		}
		else{
			return "Signature verification failed";
		}
		
	}
	
	
}
