package com.boun.structures;

import com.google.gson.Gson;


public class Block {
	
	BlockContent content;
	String blockHash;
	
	public BlockContent getContent() {
		return content;
	}
	public void setContent(BlockContent content) {
		this.content = content;
	}
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Block other = (Block) obj;
		
		Gson gson = new Gson();
		
		return gson.toJson(this).equals(gson.toJson(other));
		
	}
	
}
