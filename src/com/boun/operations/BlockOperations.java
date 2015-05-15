package com.boun.operations;


import java.io.IOException;
import java.util.ArrayList;

import com.boun.file.FileOperations;
import com.boun.hashes.HashOperations;
import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.network.mining.GetGenesisBlockRequest;
import com.boun.server.Properties;
import com.boun.signature.Generator;
import com.boun.structures.Block;
import com.boun.structures.BlockContent;
import com.boun.structures.Transaction;
import com.boun.util.DateOperations;
import com.google.gson.Gson;

public class BlockOperations {
	
	//the directory of block chain
	public static final String BLOCK_CHAIN_DIRECTORY = "/resources/chain/";
	
	/**
	 * gets a block from harddisk
	 * */
	public static Block getBlock(int blockID) throws IOException{
		System.out.println("get block:"+blockID);
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+"/"+blockID+".txt";
		String jSon = FileOperations.readFile(blockDirectory);
		System.out.println(jSon);
		Gson gson = new Gson();
		return gson.fromJson(jSon,Block.class);	
	}
	
	/**
	 * writes a block to the harddisk
	 * */
	public static void writeBlock(Block block) throws IOException{
		
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+block.getContent().getBlockID()+".txt";
		
		Gson gson = new Gson();
		String jSon = gson.toJson(block);
		
		System.out.println(blockDirectory);
		FileOperations.writeFile(blockDirectory, jSon);
	}

	public static void downloadGenesisBlocks() throws Exception{
		
		for(int i=Properties.getNumberOfDownloadedGenesisBlock()+1;i<=Properties.getNumberOfGenesisBlock();++i){
			GetGenesisBlockRequest request = new GetGenesisBlockRequest();
			request.setBlockId(-i);
			
			String blockJson = RequestSender.send(request, NetworkData.SERVER_IP);
			Gson gson = new Gson();
			
			Block block = gson.fromJson(blockJson,Block.class);
			
			BlockOperations.writeBlock(block);
			Properties.setNumberOfDownloadedGenesisBlock(i);
			
		}
		
	}
	
	

	/**
	 * coin mining
	 * */
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
