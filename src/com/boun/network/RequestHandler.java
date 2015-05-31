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
import java.io.IOException;
import java.net.Socket;

import com.boun.network.forwards.BlockForwardRequest;
import com.boun.network.forwards.TransactionForwardRequest;
import com.boun.network.gets.GetBlockRequest;
import com.boun.network.gets.GetGenesisBlockRequest;
import com.boun.network.gets.GetNumberOfBlockRequest;
import com.boun.network.gets.GetNumberOfGenesisRequest;
import com.boun.network.gets.GetTargetRequest;
import com.boun.network.peers.GetPeersRequest;
import com.boun.network.peers.NewPeerEchoRequest;
import com.google.gson.Gson;

/**
 * @author ERKAN EROL 2010400129
 *
 * Every request is handled by an object of this class. 
 *
 */
public class RequestHandler extends Thread {

	//the socket that a client connected
	private Socket socket;
	
	public RequestHandler(Socket socket) {
		this.socket = socket;
	}

	//thread method
	public void run(){		
		
		try {

			//setting input output channel
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			String stepString = in.readUTF();
			
			int inputStep = Integer.valueOf(stepString);
			
			//System.out.println("step:"+inputStep);
			
			String inputString = "";
			for(int k=0;k<inputStep;++k){
				inputString += in.readUTF();	
			}

			//System.out.println("input:"+inputString);
			
			//parses the request and process
			EncapsulatedRequest encapsulatedRequest = RequestHandler.getRequest(inputString);
			
			String resultString = RequestHandler.processRequest(encapsulatedRequest);
			
	        //System.out.println(resultString);
    
			int step = (int) Math.ceil((double)resultString.length()/1000);
			
			out.writeUTF(step+"");
			out.flush();
	       	   
			int i=0;
			for(;i<step-1;++i){
				
		        out.writeUTF(resultString.substring(i*1000,(i+1)*1000));
		       	out.flush();        
			}
			
			out.writeUTF(resultString.substring(i*1000));
	       	out.flush();        
	       	
	       	
	        out.close();
	        in.close();
	        
	        socket.close();
	        
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//converts input string to EncapsulatedRequest object
	public static EncapsulatedRequest getRequest(String jSonRequest){
		Gson gson = new Gson();
		EncapsulatedRequest request = (EncapsulatedRequest) gson.fromJson(jSonRequest, EncapsulatedRequest.class);
		return request;
	} 

	//matches the request types to its function
	public static String processRequest(EncapsulatedRequest request){
		
		Gson gson = new Gson();
		switch(request.getRequestCode()){
			
		   case NewPeerEchoRequest.REQUEST_CODE:
				NewPeerEchoRequest echo = (NewPeerEchoRequest) gson.fromJson(request.getRequestData(), NewPeerEchoRequest.class);
				return echo.handleRequest();
			
			case GetPeersRequest.REQUEST_CODE:
				GetPeersRequest get = (GetPeersRequest) gson.fromJson(request.getRequestData(), GetPeersRequest.class);
				return get.handleRequest();
			
			case GetTargetRequest.REQUEST_CODE:
				GetTargetRequest target = (GetTargetRequest) gson.fromJson(request.getRequestData(), GetTargetRequest.class);
				return target.handleRequest();
			
			case GetNumberOfGenesisRequest.REQUEST_CODE:
				GetNumberOfGenesisRequest numberOfGenesisRequest = (GetNumberOfGenesisRequest) gson.fromJson(request.getRequestData(), GetNumberOfGenesisRequest.class);
				return numberOfGenesisRequest.handleRequest();
			
			case GetGenesisBlockRequest.REQUEST_CODE:
				GetGenesisBlockRequest getGenesisBlockRequest = (GetGenesisBlockRequest) gson.fromJson(request.getRequestData(), GetGenesisBlockRequest.class);
				return getGenesisBlockRequest.handleRequest();
			
			case TransactionForwardRequest.REQUEST_CODE:
				TransactionForwardRequest transactionForwardRequest = (TransactionForwardRequest) gson.fromJson(request.getRequestData(), TransactionForwardRequest.class);
				return transactionForwardRequest.handleRequest();
			
			case GetNumberOfBlockRequest.REQUEST_CODE:
				System.out.println("GetNumberOfBlockRequest");
				GetNumberOfBlockRequest getNumberOfBlockRequest = (GetNumberOfBlockRequest) gson.fromJson(request.getRequestData(), GetNumberOfBlockRequest.class);
				return getNumberOfBlockRequest.handleRequest();
			
			case GetBlockRequest.REQUEST_CODE:
				GetBlockRequest getBlockRequest = (GetBlockRequest) gson.fromJson(request.getRequestData(), GetBlockRequest.class);
				return getBlockRequest.handleRequest();
				
			case BlockForwardRequest.REQUEST_CODE:
				BlockForwardRequest blockForwardRequest = (BlockForwardRequest) gson.fromJson(request.getRequestData(), BlockForwardRequest.class);
				return blockForwardRequest.handleRequest();
			
			
		}
		
		return null;
	}

}
