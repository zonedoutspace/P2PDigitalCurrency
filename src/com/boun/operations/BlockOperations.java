package com.boun.operations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.boun.file.FileOperations;
import com.boun.hashes.HashOperations;
import com.boun.signature.Generator;
import com.boun.structures.Block;
import com.boun.structures.BlockContent;
import com.boun.structures.Transaction;
import com.boun.util.DateOperations;
import com.google.gson.Gson;

public class BlockOperations {
	
	public static final String BLOCK_CHAIN_DIRECTORY = "chain";
	
	public static Block getBlock(int blockID) throws IOException{
		
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+File.pathSeparator+blockID;
		String jSon = FileOperations.readFile(blockDirectory);
		
		Gson gson = new Gson();
		return gson.fromJson(jSon,Block.class);	
	}
	
	public static void writeBlock(Block block) throws IOException{
		
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+File.pathSeparator+block.getContent().getBlockID();
		
		Gson gson = new Gson();
		String jSon = gson.toJson(block);
		
		FileOperations.writeFile(block.getContent().getBlockID()+"", jSon);
	}
	
	
	public static Block mine(int blockId, ArrayList<Transaction> transactions , String parentHash ,String target) throws Exception{
		
		Block block = new Block();
		Gson gson = new Gson();
		
		
		BlockContent blockContent = new BlockContent();
		blockContent.setBlockID(blockId);
		blockContent.setDate(DateOperations.getCurrentDate());
		blockContent.setMinerPublicKey(Generator.getMyPublicKeyString());
		blockContent.setParentBlockHash(parentHash);
		blockContent.setTransactions(transactions);
		
		long nonce=0;
		String hash = null;
		System.out.println("target:"+target);
		
		for(;nonce<1000000;++nonce){
			blockContent.setNonce(nonce);
			hash = HashOperations.getHash(gson.toJson(blockContent));
			
			System.out.println("nonce:"+nonce);
			System.out.println("hash:"+hash);
			
			if(hash.compareTo(target)<1){
				blockContent.setNonce(nonce);
				block.setBlockHash(hash);
				block.setContent(blockContent);
				return block;
			}
		}	
		
		return null;
				
	}
	
}
