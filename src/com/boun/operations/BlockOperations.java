package com.boun.operations;

import java.io.File;
import java.io.IOException;

import com.boun.file.FileOperations;
import com.boun.structures.Block;
import com.google.gson.Gson;

public class BlockOperations {
	
	public static final String BLOCK_CHAIN_DIRECTORY = "chain";
	
	public Block getBlock(int blockID) throws IOException{
		
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+File.pathSeparator+blockID;
		String jSon = FileOperations.readFile(blockDirectory);
		
		Gson gson = new Gson();
		return gson.fromJson(jSon,Block.class);	
	}
	
	public void writeBlock(Block block) throws IOException{
		
		String blockDirectory = BLOCK_CHAIN_DIRECTORY+File.pathSeparator+block.getContent().getBlockID();
		
		Gson gson = new Gson();
		String jSon = gson.toJson(block);
		
		FileOperations.writeFile(block.getContent().getBlockID()+"", jSon);
		
	}
	
	
}
