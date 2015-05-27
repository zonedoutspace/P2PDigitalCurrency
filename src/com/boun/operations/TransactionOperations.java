package com.boun.operations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import com.boun.server.Properties;
import com.boun.signature.Base58;
import com.boun.signature.Generator;
import com.boun.structures.Block;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionInput;
import com.boun.structures.TransactionOutput;
import com.boun.util.DateOperations;
import com.google.gson.Gson;


public class TransactionOperations {

	public static String checkTransaction(Transaction transaction) throws Exception{

		if(transaction==null){
			return "Error: Transaction is null";
		}

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

			result = checkInput(inputs.get(i),transaction.getSenderPublicKey());

			if(result!=null){
				return result;
			}

		}

		return result;
	}

	private static String checkInput(TransactionInput input,String senderPublicKey) {


		try {
			Block block = BlockOperations.getBlock(input.getBlockNumber());

			if(block==null){
				return "Error:Block number";
			}
			else{

				try{
					TransactionOutput output = block.getContent()
							.getTransactions()
							.get(input.getTransactionOrder())
							.getContent().getOutputs().get(input.getOutputOrder());
					
					if(!output.getReceiverPublicKey().equals(senderPublicKey)){
						return "The input belongs another person";
					}
					
					if(output.getAmount()!=input.getAmount()){
						return "Amounts are not equal. output:"+output.getAmount()+" input:"+input.getAmount();
					}
					
					//in this point the input is true. but we don't know whether it is spent or not
					
					return checkUnspentInput(input);

				}
				catch(Exception e){
					e.printStackTrace();
					return "No such output";
				}
			}


		} catch (IOException e) {
			e.printStackTrace();
			return "Error:Block number";
		}

	}

	private static String checkUnspentInput(TransactionInput input) {
		
		int downloadedBlocks = Properties.getNumberOfDownloadedBlock();
		
		int startBlockInChain;
		
		if(input.getBlockNumber()<0){
			startBlockInChain=1;
		}
		else{
			startBlockInChain=input.getBlockNumber()+1;
		}
		
		//looks all blocks
		for(int i=startBlockInChain;i<=downloadedBlocks;++i){
			
			try {
				Block block = BlockOperations.getBlock(i);
				
				ArrayList<Transaction> transactions = block.getContent().getTransactions();
				
				//looks all transaction in the block
				for(int k=0;k<transactions.size();++k){
				
					Transaction transaction = transactions.get(k);
					
					ArrayList<TransactionInput> inputs = transaction.getContent().getInputs();
					
					//looks all inputs in the transaction
					for(int l=0;l<inputs.size();++l){
						TransactionInput input2 = inputs.get(l);
						
						if(input.equals(input2)){
							return "It is spent";
						}
						
					}
					
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
				return "Block error";
			}
			
			
		}
		
		return null;
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

	public static void main(String[] args) throws Exception{

		//block -1, transaction order 10
		//use genesis and normal blocks together

		Transaction transaction = new Transaction();

		TransactionContent content = new TransactionContent();

		TransactionInput input1 = new TransactionInput();
		input1.setBlockNumber(-1);
		input1.setTransactionOrder(10);
		input1.setOutputOrder(0);
		input1.setAmount(100);


		TransactionInput input2 = new TransactionInput();
		input2.setBlockNumber(1);
		input2.setTransactionOrder(0);
		input2.setOutputOrder(1);
		input2.setAmount(150);		

		TransactionInput input3 = new TransactionInput();
		input3.setBlockNumber(1);
		input3.setTransactionOrder(1);
		input3.setOutputOrder(1);
		input3.setAmount(150);		


		ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);



		//block -1, transaction 5
		TransactionOutput output = new TransactionOutput();
		output.setAmount(250);
		output.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgMW2xEB3LTViNVRiNZNuG3GnYt3ZBFz5SmhQLSh6rJ1LZYaW1VPXVYDpaAywwUJtPKnbtqBfgndtyCSpJTrsKzHy78qzrs2");

		//block -1, transaction 3
		TransactionOutput output1 = new TransactionOutput();
		output1.setAmount(150);
		output1.setReceiverPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgZQuQfo1yj5JzjBFCJsURMrFuCKiwr3S2Z6gwvGuAD2yTxoRHX3K9dB7mboVLppWWMqQB1kPVj9onAxJmiFYzjKi86keuDa");


		ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
		outputs.add(output);
		outputs.add(output1);

		content.setInputs(inputs);
		content.setOutputs(outputs);
		content.setTransationDate(DateOperations.getCurrentDate());

		Gson gson = new Gson();


		transaction.setContent(content);
		transaction.setSenderPublicKey("rbanryFCC6yJnCRCQgVWyYXvqkSBz4k8KgotQeCDqLHxfBCFV37Sdxu3KcKCtgmAWAbvvA5ZCehvBpag8WtJhejSfKgzGHhc86WfKprgJ2PM39QcWpJCq9Avt6EEMUiU");
		transaction.setSign(Generator.sign(Generator.getPrivateKey("23g8Q4bQCNC9fo3NhW2KdUsBUqKZp1exZTmQob7mRM4uyva4cVD1e6vb4x2EwnNuJjoyhXeBXBMKbA1RcCXRhyR21e5vCfnxX7srue6haW9YaNg2Lbc2qp1upTG6zuaz4rJA6ypksG5dRzbCtdUsL14G8CjH7Wt13ZpWYZuZpEkyEYEsJ7ngLgvFAmRjQ6ruuGrHAwowHa9zSuBA5LkdN7Nw8L8xrz3yUtLq9MPt51JaEnzKFWjDxefntznhtJvaB1bEx4YpwpDARpYv58bF7nRaa324hxPAEZHBxmJUodezxB1kvsLQVHMFj854gLqQrx1hrT4jdU47WcgqFUx4f2X23C4jouiM1n4M8hPn7cH2xxhGi3xjdVyti367qKwvLs3cFBme8vKh4pJiYfTUPPXxDgacuyizxvU7PnTwaUrLfgSbwJPR1d7fyYHnfn8YNabAvfBdQy4LGWjQczEXj"), gson.toJson(content)));


		System.out.println(checkTransaction(transaction));
	}

	
}
