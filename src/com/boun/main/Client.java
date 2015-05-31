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

package com.boun.main;

import java.util.Scanner;

import com.boun.file.FileOperations;
import com.boun.mining.Pool;
import com.boun.network.NetworkData;
import com.boun.network.NetworkListener;
import com.boun.network.RequestSender;
import com.boun.network.forwards.TransactionForwardRequest;
import com.boun.network.forwards.TransactionForwardThread;
import com.boun.network.gets.GetNumberOfGenesisRequest;
import com.boun.operations.BlockOperations;
import com.boun.operations.PeerOperation;
import com.boun.operations.TransactionOperations;
import com.boun.server.CreateGenesis;
import com.boun.server.Properties;
import com.boun.signature.Generator;
import com.boun.structures.Transaction;
import com.boun.user.TransactionInteractions;

public class Client {

	public static NetworkListener listener;
	
	
	public static void main(String[] args) {	
		
		
		
		System.out.println("Welcome");	
		
		System.out.println("File and directories are being created.");
		try {
			FileOperations.setup();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Setup Error");
			System.exit(1);
		}
				
		
		System.out.println("Getting the number of genesis block...");
			
		GetNumberOfGenesisRequest genesisRequest = new GetNumberOfGenesisRequest();
		try {
			String result = RequestSender.send(genesisRequest, NetworkData.SERVER_IP);
			Properties.setNumberOfGenesisBlock(Integer.valueOf(result));
			System.out.println("number of genesis"+result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: The number of genesis block cannot be downloaded.");
			System.exit(1);
		}			

		System.out.println("Genesis blocks are being downlaoded.");
		
		try {
			BlockOperations.downloadGenesisBlocks();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Genesis blocks cannot be downloaded.");
			System.exit(1);
		}

		
		System.out.println("Downloading peers' ip addresses...");
		
		try {
			PeerOperation.getAllPeersFromServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Peer address cannot be downloaded.");
			System.exit(1);
		}
		
		
		System.out.println("Blocks numbers are being downloaded");		
	
		try{
			BlockOperations.getBlockNumberFromPeers();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Number of blocks cannot be downloaded.");
			System.exit(1);
		}
		
		
		//indir onları
	
		
		


		System.out.println("Network Listener is starting.");
		
		listener = new NetworkListener();
		listener.start();
		
		System.out.println("Network is listening.");
		
		
		
		System.out.println("IP address is being sent to server...");
		try {
			PeerOperation.sendIpToServer();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ip adress cannot be sent.");
			System.exit(1);
		}
		
		
		
		System.out.println("Sending ip to other peers");
		PeerOperation.sendIpToPeers();
		
		showMenu();
		
		
		
	}


	private static void showMenu() {
		
		while(true){
			
			displayOptions();
			int selection = getSelection();
			System.out.println("selection"+selection);
			switch (selection) {
				case 1:
					createKeys();
					break;
				case 2:
					newTransaction();
					break;
				case 3:
					return;	
				default:
					break;
			}
		}
		
	}
	
	private static void createKeys() {
		try {
			Generator.createMyKeys();
		} catch (Exception e) {
			System.out.println("The keys cannot be generated.");
			e.printStackTrace();
		}		
	}


	private static void newTransaction() {
		try {
			Transaction transaction = TransactionInteractions.createNewTransaction();
			
			String result = TransactionOperations.checkTransaction(transaction);
			
			if(!result.equals("Verified")){
				System.out.println(result);
			}
			else{
				System.out.println("It will be added to the pool");
				String poolAdd = Pool.addTransaction(transaction);
				
				if(poolAdd!=null){
					System.out.println(poolAdd);
				}
				else{
					System.out.println("It will be forwarded.");
					TransactionForwardThread thread = new TransactionForwardThread(transaction, NetworkData.getMyIp());
					thread.run();
					System.out.println("The forward thread is working");
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		
	}


	public static void displayOptions(){
		
		System.out.println("Menu");
		System.out.println("1) Generate keys");
		System.out.println("2) Create a transaction");
		System.out.println("3) Exit");
		
	}
	
	
	public static int getSelection(){
		
		System.out.println("get selection start");
		Scanner scanner = new Scanner(System.in);
		
		
		int selection = Integer.valueOf(scanner.nextLine());
 
		
		System.out.println("get selection end");
		
		return selection;
	}

}
