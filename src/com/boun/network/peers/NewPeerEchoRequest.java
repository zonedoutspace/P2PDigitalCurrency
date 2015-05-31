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

package com.boun.network.peers;


import com.boun.network.NetworkData;
import com.boun.network.Request;

public class NewPeerEchoRequest extends Request {

	public static final int REQUEST_CODE = 1;
	
	public String ip;
	
	public String handleRequest() {
		NetworkData.AddPeer(ip);
		System.out.println("New Peer is added:"+ip);
		return "1";
	}

	public int getRequestCode() {
		return REQUEST_CODE;
	}

	@Override
	public void initializeRequest() throws Exception {
		ip = NetworkData.getMyIp();
		System.out.println("my ip:"+ip);
	}

}
