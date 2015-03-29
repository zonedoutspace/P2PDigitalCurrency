package com.boun.server;

import java.io.File;

import com.boun.file.FileOperations;
import com.boun.signature.Generator;
import com.boun.structures.StringsKeyPair;
import com.boun.structures.Transaction;
import com.boun.structures.TransactionContent;
import com.boun.structures.TransactionInput;
import com.boun.structures.TransactionOutput;

public class CreateGenesis {

	
	public static final int NUMBER_OF_COINS = 1000;
	public static final int NUMBER_OF_USER = 10;

	public static final String PUBLIC_KEYS_DIRECTORY = "/resources/genesis/keys/";
	
	
	public static void createGenesis() throws Exception{
		
		for(int i=0;i<NUMBER_OF_USER;++i){
			System.out.println(i);
			StringsKeyPair keyPair = Generator.createKeys();
			
			FileOperations.writeFile(PUBLIC_KEYS_DIRECTORY+keyPair.getPublicKey(), keyPair.getPrivateKey());
			
			TransactionOutput output = new TransactionOutput();
			output.setAmount(NUMBER_OF_COINS/NUMBER_OF_USER);
			output.setReceiverPublicKey(keyPair.getPublicKey());

			
			

		}
		
	}
	
	public static void main(String[] args) throws Exception{
		createGenesis();
	}
	
	
	
	
}
