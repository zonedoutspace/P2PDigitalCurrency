package com.boun.main;

import java.util.Scanner;

import com.boun.file.FileOperations;
import com.boun.network.NetworkListener;
import com.boun.server.CreateGenesis;

public class Server {
	
	
	public static NetworkListener listener;

	public static void main(String[] args){
		

		
		System.out.println("Server is starting");
		
		System.out.println("File and directories are being created.");
		
		try {
			FileOperations.setup();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Setup Error");
			System.exit(1);
		}
		
		listener = new NetworkListener();
		listener.start();
		
		System.out.println("Network is listening.");
		
		showMenu();
		
		
		System.exit(0);
		
	}
	
	public static void showMenu(){
		
		while(true){
			
			displayOptions();
			int selection = getSelection();
			System.out.println("selection"+selection);
			switch (selection) {
				case 1:
					
					try {
						CreateGenesis.createGenesis();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case 2:
					return;
				default:
					break;
			}
		}
	
	} 
	
	
	
	public static void displayOptions(){
		
		System.out.println("Menu");
		System.out.println("1) Create a genesis block");
		System.out.println("2) Exit");
		
	}
	
	public static int getSelection(){
		
		System.out.println("get selection start");
		Scanner scanner = new Scanner(System.in);
		
		
		int selection = Integer.valueOf(scanner.nextLine());
 
		
		System.out.println("get selection end");
		
		return selection;
	}
	
	
}
