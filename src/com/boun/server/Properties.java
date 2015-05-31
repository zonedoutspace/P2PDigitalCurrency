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
