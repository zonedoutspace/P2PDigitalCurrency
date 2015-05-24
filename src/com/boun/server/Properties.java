package com.boun.server;

import java.io.IOException;

import com.boun.file.FileOperations;

public class Properties {
	
	public static final String NUMBER_OF_GENESIS = "/resources/properties/number_of_genesis.txt";
	public static final String NUMBER_OF_DOWNLOADED_GENESIS = "/resources/properties/number_of__downloaded_genesis.txt";
	
	public static final String NUMBER_OF_BLOCKS = "/resources/properties/number_of_blocks.txt";
	public static final String NUMBER_OF_DOWNLOADED_BLOCKS = "/resources/properties/number_of__downloaded_blocks.txt";
	
		
	public static final String TARGET = "13zRNNVkhkBQf4a9zxivKWjbirJKv7BRdfnW6Cj7";
	
	
	
	public static int getNumberOfGenesisBlock(){
		
		String s;
		try {
			s = FileOperations.readFile(NUMBER_OF_GENESIS);
			return Integer.valueOf(s);
		} catch (Exception e) {
			try {
				FileOperations.writeFile(NUMBER_OF_GENESIS, "0");
			} catch (IOException e1) {
				return 0;
			}
			return 0;			
		}
	}
	
	public static void setNumberOfGenesisBlock(int number) throws Exception{
		
		FileOperations.writeFile(NUMBER_OF_GENESIS, ""+number);
	}
	
	public static int getNumberOfDownloadedGenesisBlock(){
		
		String s;
		try {
			s = FileOperations.readFile(NUMBER_OF_DOWNLOADED_GENESIS);
			return Integer.valueOf(s);
		} catch (Exception e) {
			try {
				FileOperations.writeFile(NUMBER_OF_DOWNLOADED_GENESIS, "0");
			} catch (IOException e1) {
				return 0;
			}
			return 0;			
		}
	}
	
	public static void setNumberOfDownloadedGenesisBlock(int number) throws Exception{
		
		FileOperations.writeFile(NUMBER_OF_DOWNLOADED_GENESIS, ""+number);
	}
	
	
	public static int getNumberOfBlock(){
		
		String s;
		try {
			s = FileOperations.readFile(NUMBER_OF_BLOCKS);
			return Integer.valueOf(s);
		} catch (Exception e) {
			try {
				FileOperations.writeFile(NUMBER_OF_BLOCKS, "0");
			} catch (IOException e1) {
				return 0;
			}
			return 0;			
		}
	}
	
	public static void setNumberOfBlock(int number) throws Exception{
		
		FileOperations.writeFile(NUMBER_OF_BLOCKS, ""+number);
	}
	
	
	public static int getNumberOfDownloadedBlock(){
		
		String s;
		try {
			s = FileOperations.readFile(NUMBER_OF_DOWNLOADED_BLOCKS);
			return Integer.valueOf(s);
		} catch (Exception e) {
			try {
				FileOperations.writeFile(NUMBER_OF_DOWNLOADED_BLOCKS, "0");
			} catch (IOException e1) {
				return 0;
			}
			return 0;			
		}
	}
	
	public static void setNumberOfDownloadedBlock(int number) throws Exception{
		
		FileOperations.writeFile(NUMBER_OF_DOWNLOADED_BLOCKS, ""+number);
	}
	
	
}
