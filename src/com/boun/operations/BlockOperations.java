package com.boun.operations;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.boun.file.FileOperations;
import com.boun.hashes.HashOperations;
import com.boun.network.NetworkData;
import com.boun.network.RequestSender;
import com.boun.network.gets.GetBlockRequest;
import com.boun.network.gets.GetGenesisBlockRequest;
import com.boun.network.gets.GetNumberOfBlockRequest;
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
		//System.out.println(jSon);
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
	
	public static int downloadBlocks(){
		
		int startBlockId = Properties.getNumberOfDownloadedBlock();
		int endBlockId = Properties.getNumberOfBlock();
		
		for(int i=startBlockId;i<=endBlockId;++i){
			
			try{
				boolean result = downloadBlock(i);
				
				if(!result){
					return i-1;
				}
				
			}
			catch(Exception e){
				e.printStackTrace();
				return i-1;
			}
		}
		
		return endBlockId;
	} 
	
	
	public static boolean downloadBlock(int blockId) throws Exception{
		Block block = getBlockFromPeers(blockId);
		
		if(block!=null){
			System.out.println(blockId+ " is downloaded");
			writeBlock(block);
			Properties.setNumberOfDownloadedBlock(blockId);
			return true;
		}
		else{
			return false;
		}
	}
	
	public static void getBlockNumberFromPeers() throws Exception{
			
		String[] peerList = NetworkData.getPeersAsList();
		
		Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
		
		//get block numbers from peers
		for(int i=0;i<peerList.length;++i){
			
			
			
			try{
				
				System.out.println("getting # of blocks from " + peerList[i]);
				
				GetNumberOfBlockRequest request = new GetNumberOfBlockRequest();
				
				String result = RequestSender.send(request, peerList[i]);
				int blockNumber = Integer.valueOf(result);
				
				System.out.println(peerList[i]+" says  #of block is:"+blockNumber);
				
				int count = freq.containsKey(blockNumber) ? freq.get(blockNumber) : 0;
				freq.put(blockNumber,count+1);				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		//use most frequent
		
		Map.Entry<Integer, Integer> maxEntry = null;
		
		for (Map.Entry<Integer, Integer> entry : freq.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		
		int result;
		if(maxEntry==null){
			result = 0;
		}
		else{
			result = maxEntry.getKey();
		}
		
		System.out.println("number of block is " + result);
		
		Properties.setNumberOfBlock(result);
		
		
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
	
	public static Block getBlockFromPeers(int blockId){
		
		String[] peerList = NetworkData.getPeersAsList();
	
		
		Map<Block, Integer> freq = new HashMap<Block, Integer>();
		
		
		for(int i=0;i<peerList.length;++i){
			
			try {
				Block block = getBlockFromAPeer(blockId, peerList[i]);
				
				if(block!=null){
					int count = freq.containsKey(block) ? freq.get(block) : 0;
					freq.put(block,count+1);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
			
		}
		
		
		//use most frequent
		
		Map.Entry<Block, Integer> maxEntry = null;
		
		for (Map.Entry<Block, Integer> entry : freq.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
		    {
		        maxEntry = entry;
		    }
		}
		
		return maxEntry.getKey();
		
	}
	
	
	
	public static Block getBlockFromAPeer(int blockId,String peerIp) throws Exception{
			
		GetBlockRequest request = new GetBlockRequest();
			
		String blockText = RequestSender.send(request, peerIp);
		
		Gson gson = new Gson();
		
		return gson.fromJson(blockText, Block.class); 
				
		
	}
	
}
