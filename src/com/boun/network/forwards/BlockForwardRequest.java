package com.boun.network.forwards;

import com.boun.network.NetworkData;
import com.boun.network.Request;
import com.boun.structures.Block;

public class BlockForwardRequest extends Request{

	public static final int REQUEST_CODE = 9;
	
	public Block block;
	public String senderIp;
	
	@Override
	public int getRequestCode() {
		return REQUEST_CODE;
	}

	@Override
	public void initializeRequest() throws Exception {
		senderIp = NetworkData.getMyIp();
		System.out.println("my ip:"+senderIp);
		
	}

	@Override
	public String handleRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	
	
}
