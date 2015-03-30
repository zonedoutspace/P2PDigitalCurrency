package com.boun.network.peers;

import com.boun.network.NetworkData;
import com.boun.network.Request;
import com.google.gson.Gson;

public class GetPeersRequest extends Request {

	public static final int REQUEST_CODE = 2;
	
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
		Gson gson = new Gson();
		return gson.toJson(NetworkData.getPeerList());		
	}

}
