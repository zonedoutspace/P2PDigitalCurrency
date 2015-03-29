package com.boun.main;

import com.boun.network.NetworkListener;

public class Server {

	public static void main(String[] args){
		
		
		System.out.println("server is starting");
		
		
		NetworkListener listener = new NetworkListener();
		listener.start();
		
		System.out.println("Network is listening.");
	}
}
