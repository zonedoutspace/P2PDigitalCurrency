package com.boun.network.mining;

import com.boun.network.Request;
import com.boun.server.Properties;

public class GetNumberOfGenesisRequest extends Request {

	public static final int REQUEST_CODE = 4;
	
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
		return ""+Properties.getNumberOfGenesisBlock();
	}

}
