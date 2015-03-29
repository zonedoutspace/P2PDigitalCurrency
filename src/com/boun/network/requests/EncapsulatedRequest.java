package com.boun.network.requests;

/**
 * @author ERKAN EROL 2010400129
 * 
 * The format of requests
 *
 */
public class EncapsulatedRequest {
	
	//to differentiate requests
	int requestCode;
	
	//keeps a corresponding object to the request
	String requestData;
	
	public EncapsulatedRequest(int requestCode,String requestData){
		super();
		this.requestCode = requestCode;
		this.requestData = requestData;
	}

	public int getRequestCode() {
		return requestCode;
	}

	public void setRequestCode(int requestCode) {
		this.requestCode = requestCode;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}
		
}
