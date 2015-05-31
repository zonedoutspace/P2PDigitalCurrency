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

	public static final String GENESIS_DIRECTORY = "/resources/genesis/";
	public static final String GENESIS_KEYS_DIRECTORY = "/resources/genesis/keys/";
	
	public static final String PUBLIC_KEYS_DIRECTORY = "/resources/genesis/keys/public/";
	public static final String PRIVATE_KEYS_DIRECTORY = "/resources/genesis/keys/private/";
	
	
	
	
	public static void createGenesis() throws Exception{
		
		int block_id = Properties.getNumberOfGenesisBlock()+1;
		System.out.println("block id:"+block_id);
		String parent_hash;
		if(block_id==1){
			parent_hash = "NO PARENT FOR GENESIS";
		}
		else{
			parent_hash = BlockOperations.getBlock(-(block_id-1)).getBlockHash();
		}
		 

		//creates a directory for keys in the block
		FileOperations.createADirectory(PUBLIC_KEYS_DIRECTORY+block_id);
		FileOperations.createADirectory(PRIVATE_KEYS_DIRECTORY+block_id);
		
		//array of transaction in a block
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		
		//for converting the block
		Gson gson = new Gson();
		
		//in each block,new users are created and added to the sytem
		for(int j=0;j<NUMBER_OF_USER_PER_BLOCK;++j){

			//key of new user
			StringsKeyPair keyPair = Generator.createKeys();
			
			//new keys are stored
			FileOperations.writeFile(PUBLIC_KEYS_DIRECTORY+block_id+"/"+j, keyPair.getPublicKey());				
			FileOperations.writeFile(PRIVATE_KEYS_DIRECTORY+block_id+"/"+j, keyPair.getPrivateKey());						
			
			
			//new transaction for a user
			Transaction transaction = new Transaction();
			
			//outputs array of the transaction
			ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
			
			
			for(int k=0;k<NUMBER_OF_COINS_PER_BLOCK/(NUMBER_OF_USER_PER_BLOCK*NUMBER_OF_COINS_PER_OUTPUT);++k){
				
				//creates an putput
				TransactionOutput output = new TransactionOutput();
				
				output.setAmount(NUMBER_OF_COINS_PER_OUTPUT);
				output.setReceiverPublicKey(keyPair.getPublicKey());
				
				//added to the array
				outputs.add(output);
				
			}
			
			//creates a transaction content
			TransactionContent content = new TransactionContent();
			
			//inputs are empty since it is genesis.
			content.setInputs(null);
			
			content.setOutputs(outputs);
			content.setTransationDate(DateOperations.getCurrentDate());			
			
			transaction.setContent(content);
			transaction.setSign(Generator.sign(gson.toJson(content)));
			transaction.setSenderPublicKey(keyPair.getPublicKey());
			
			transactions.add(transaction);	
		}
		
		

		
		Block genesis = BlockOperations.mine(-block_id, transactions,parent_hash,Properties.TARGET);
		
		String genesisFile = gson.toJson(genesis);
		
		FileOperations.writeFile(GENESIS_DIRECTORY+"-"+block_id+".txt",genesisFile);
		BlockOperations.writeBlock(genesis);
		
		Properties.setNumberOfGenesisBlock(block_id);		
				
	}
	
	public static void main(String[] args) throws Exception{	
		
		createGenesis();
		
	}
	
	
}
