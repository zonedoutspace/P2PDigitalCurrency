package com.boun.main;

import com.boun.network.NetworkListener;

public class Client {

	public static void main(String[] args) {
		
		System.out.println("Hello the program");
		System.out.println("Downloading peers' ip addresses...");
		
		
		NetworkListener listener = new NetworkListener();
		listener.start();
		
		
		System.out.println("The program is finishing.");
		
		

	}

}
