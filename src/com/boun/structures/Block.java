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
