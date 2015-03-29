package com.boun.network;

import com.google.gson.Gson;


/**
 * @author ERKAN EROL 2010400129
 *
 * the structure of requests
 */
public abstract class Request {
			
	public abstract int getRequestCode();
	public abstract void initializeRequest() throws Exception;

	//handle the request and return outputs
	public abstract  String handleRequest();
	
	//for clients
	public String createRequest() throws Exception{		
		Gson gson = new Gson();
		EncapsulatedRequest en= new EncapsulatedRequest(this.getRequestCode(),gson.toJson(this));
		return gson.toJson(en);
	}
	
}
