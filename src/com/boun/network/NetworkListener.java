package com.boun.network;

import java.io.IOException;
import java.net.ServerSocket;

public class NetworkListener extends Thread{


	public void run(){

		ServerSocket listener = null;
		try {
			listener = new ServerSocket(NetworkData.APP_PORT);
			while (true) {
				new RequestHandler(listener.accept()).start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally {
			
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
