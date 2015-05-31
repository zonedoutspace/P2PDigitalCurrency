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

