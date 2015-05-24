package com.boun.network.gets;

import java.io.IOException;

import com.boun.network.Request;
import com.boun.operations.BlockOperations;
import com.boun.structures.Block;
import com.google.gson.Gson;

public class GetBlockRequest extends Request{

	public static final int REQUEST_CODE = 8;
	
	public int blockId;

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}
	
	

	@Override
	public int getRequestCode() {
		
		return REQUEST_CODE;
		
	}

	@Override
	public void initializeRequest() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String handleRequest() {
		
		try {
			Gson gson = new Gson();
			Block block = BlockOperations.getBlock(blockId);
			return gson.toJson(block);				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
}

