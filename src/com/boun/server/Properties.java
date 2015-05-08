package com.boun.server;

import java.io.IOException;

import com.boun.file.FileOperations;

public class Properties {
	
	public static final String NUMBER_OF_GENESIS = "/resources/properties/number_of_genesis.txt";
	
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
	
	public static final String TARGET = "13zRNNVkhkBQf4a9zxivKWjbirJKv7BRdfnW6Cj7";
	
	
}
