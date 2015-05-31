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
