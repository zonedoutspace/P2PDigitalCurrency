package com.boun.server;

import java.util.ArrayList;

import com.boun.file.FileOperations;
import com.boun.operations.BlockOperations;
import com.boun.signature.Generator;
import com.boun.structures.Block;
import com.boun.structures.StringsKeyPair;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionOutput;
import com.boun.util.DateOperations;
import com.google.gson.Gson;

public class CreateGenesis {

	public static final int NUMBER_OF_BLOCKS = 10;
	public static final int NUMBER_OF_COINS_PER_BLOCK = 100000;
	public static final int NUMBER_OF_USER_PER_BLOCK = 100;
	public static final int NUMBER_OF_COINS_PER_OUTPUT= 100;

	public static final String PUBLIC_KEYS_DIRECTORY = "/resources/genesis/keys/public/";
	public static final String PRIVATE_KEYS_DIRECTORY = "/resources/genesis/keys/private/";
	
	public static final String GENESIS_BLOCKS_DIRECTORY = "/resources/genesis/";
	
	
	public static void createGenesis() throws Exception{
		
		String parent_hash = "NO PARENT FOR GENESIS"; 
		
		for(int b=1;b<=NUMBER_OF_BLOCKS;++b){
			
			
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			
			Gson gson = new Gson();
			
			for(int i=0;i<NUMBER_OF_USER_PER_BLOCK;++i){
				//System.out.println(i);
				StringsKeyPair keyPair = Generator.createKeys();
				
				FileOperations.writeFile(PUBLIC_KEYS_DIRECTORY+b+"/"+i, keyPair.getPublicKey());
				FileOperations.writeFile(PRIVATE_KEYS_DIRECTORY+b+"/"+i, keyPair.getPrivateKey());
				
				Transaction transaction = new Transaction();
				
				ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
				
				for(int k=0;k<NUMBER_OF_COINS_PER_BLOCK/(NUMBER_OF_USER_PER_BLOCK*NUMBER_OF_COINS_PER_OUTPUT);++k){
					TransactionOutput output = new TransactionOutput();
					output.setAmount(NUMBER_OF_COINS_PER_OUTPUT);
					output.setReceiverPublicKey(keyPair.getPublicKey());
					
					outputs.add(output);
				}
				
				
				TransactionContent content = new TransactionContent();
				content.setInputs(null);
				content.setOutputs(outputs);
				content.setTransationDate(DateOperations.getCurrentDate());			
				
				transaction.setContent(content);
				transaction.setSign(Generator.sign(gson.toJson(content)));
				transaction.setSenderPublicKey(keyPair.getPublicKey());
				
				transactions.add(transaction);	
			}
			
			

			
			Block genesis = BlockOperations.mine(-b, transactions,parent_hash,Properties.TARGET);
			
			String genesisFile = gson.toJson(genesis);
			
			FileOperations.writeFile(GENESIS_BLOCKS_DIRECTORY+"-"+b+".txt",genesisFile);
			
			parent_hash = genesis.getBlockHash();
			
		}
		
				
	}
	
	public static void main(String[] args) throws Exception{
		createGenesis();
	}
	
	
}
