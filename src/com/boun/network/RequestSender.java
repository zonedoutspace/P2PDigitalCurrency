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

package com.boun.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestSender {

	public static String send(Request request,String ipAddress) throws Exception {

		Socket  client = new Socket(ipAddress, NetworkData.APP_PORT);

		OutputStream outToServer = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(outToServer);

		String output = request.createRequest();
		
		int outputStep = (int) Math.ceil((double)output.length()/1000);
		
		out.writeUTF(outputStep+"");
		out.flush();
		
		int i=0;
		for(;i<outputStep-1;++i){
			
	        out.writeUTF(output.substring(i*1000,(i+1)*1000));
	       	out.flush();        
		}
		
		out.writeUTF(output.substring(i*1000));
       	out.flush();  

		InputStream inFromServer = client.getInputStream();
		DataInputStream in = new DataInputStream(inFromServer);

		String stepString = in.readUTF();
		
		int step = Integer.valueOf(stepString);
		
		String result = "";
		for(int k=0;k<step;++k){
			result += in.readUTF();	
		}
		
		//System.out.println("result:"+result);

		client.close();
		out.close();
		in.close();

		return result;

	}

}
