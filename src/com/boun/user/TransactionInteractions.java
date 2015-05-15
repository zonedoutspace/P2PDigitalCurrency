package com.boun.user;

import java.util.ArrayList;
import java.util.Scanner;

import com.boun.operations.TransactionOperations;
import com.boun.signature.Generator;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionInput;
import com.boun.structures.TransactionOutput;
import com.boun.util.DateOperations;
import com.google.gson.Gson;

public class TransactionInteractions {

	public static Transaction createNewTransaction() throws Exception{
		
		Transaction transaction = new Transaction();
		TransactionContent content = new TransactionContent();
		
		Scanner scanner = new Scanner(System.in);
		Gson gson = new Gson();
		
		System.out.println("Enter the number of inputs");
		int numberOfInputs = scanner.nextInt();
		
		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		
		for(int i=1;i<=numberOfInputs;++i){
			
			TransactionInput input = new TransactionInput();
			
			
			System.out.println("Enter "+i+"th input amount");
			int amount = scanner.nextInt();

			System.out.println("Enter "+i+"th input block number");
			int blockNumber = scanner.nextInt();
			
			System.out.println("Enter "+i+"th input transaction order");
			int tranactionOrder = scanner.nextInt();
			
			System.out.println("Enter "+i+"th input output order");
			int outputOrder = scanner.nextInt();
			
			
			
			
			input.setBlockNumber(blockNumber);
			input.setOutputOrder(outputOrder);
			input.setAmount(amount);
			input.setTransactionOrder(tranactionOrder);
			
			inputs.add(input);
			
		}
		
		
		System.out.println("Enter the number of outputs");
		int numberOfOutputs = scanner.nextInt();
		
		ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
		
		for(int i=1;i<=numberOfOutputs;++i){
			
			TransactionOutput output = new TransactionOutput();
			
			
			System.out.println("Enter "+i+"th output amount");
			int amount = scanner.nextInt();

			System.out.println("Enter "+i+"th output receiver public key");
			String receiverPublicKey = scanner.next();
			
			output.setAmount(amount);
			output.setReceiverPublicKey(receiverPublicKey);
			
			outputs.add(output);
			
		}		
		
		content.setInputs(inputs);
		content.setOutputs(outputs);
		content.setTransationDate(DateOperations.getCurrentDate());
		
		transaction.setContent(content);
		transaction.setSenderPublicKey(Generator.getMyPublicKeyString());				
		transaction.setSign(Generator.sign(gson.toJson(content)));
		
		scanner.close();
		
		
		return transaction;
	}
	
	public static void main(String[] args) throws Exception{
		
		Transaction transaction =  createNewTransaction();
	
		System.out.println(TransactionOperations.checkTransaction(transaction));
		
		
		
	}
	
}
