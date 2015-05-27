package com.boun.network.gets;

import java.io.IOException;

import com.boun.file.FileOperations;
import com.boun.network.Request;
import com.boun.operations.BlockOperations;
import com.boun.server.CreateGenesis;
import com.boun.structures.Block;
import com.google.gson.Gson;

public class GetGenesisBlockRequest extends Request{
	
	public static final int REQUEST_CODE = 5;
	
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
			Block block = BlockOperations.getBlock(blockId);
			Gson gson = new Gson();
			return gson.toJson(block);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
