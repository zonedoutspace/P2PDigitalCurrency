package com.boun.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;




import com.boun.network.requests.EncapsulatedRequest;
import com.boun.network.requests.NewPeerEchoRequest;
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
			
			//gets input from client
			String inputString = in.readUTF();
			
			//System.out.println(inputString);
			
			//parses the request and process
			EncapsulatedRequest encapsulatedRequest = RequestHandler.getRequest(inputString);
			
			String resultString = RequestHandler.processRequest(encapsulatedRequest);
			
	        //System.out.println(resultString);
    
	       	        
			//writes output to the client
	        out.writeUTF(resultString);
	        
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
		}
		
		return null;
	}

}
