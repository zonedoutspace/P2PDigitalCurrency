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
package com.boun.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.boun.server.CreateGenesis;
import com.boun.signature.Generator;


public class FileOperations {

	public static void setup() throws Exception{
		
		
		createDirectories();
		
	}
	
	public static void writeFile(String fileName,String content) throws IOException {
		
		System.out.println(System.getProperty("user.dir")+fileName+"  will be written.");
		File file = new File(System.getProperty("user.dir")+fileName);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}
	
	public static String readFile(String fileName) throws IOException {
		System.out.println("read:"+System.getProperty("user.dir")+fileName);
		File file = new File(System.getProperty("user.dir")+fileName);
		
		if (!file.exists()) {
			return null;
		}
		
		FileReader fr = new FileReader(file.getAbsoluteFile());
		
		BufferedReader br = new BufferedReader(fr);
		
		String content ="";
		String next;
		while ((next = br.readLine()) != null) {
			content+=next;
		}
		
		br.close();
		return content;
		
		
	}

	public static void createDirectories(){
		
		createADirectory("/resources");
		
		createADirectory(CreateGenesis.GENESIS_DIRECTORY);
		
		createADirectory(CreateGenesis.GENESIS_KEYS_DIRECTORY);
		
		createADirectory(CreateGenesis.PUBLIC_KEYS_DIRECTORY);
		
		createADirectory(CreateGenesis.PRIVATE_KEYS_DIRECTORY);
		
		createADirectory("/resources/chain");
		
		createADirectory("/resources/keys");
		
		
		createADirectory("/resources/properties");
		
		
		
	} 
	
	public static void createADirectory(String dir){
		
		File directory = new File(System.getProperty("user.dir")+dir);
		
		if(!directory.exists()){
			directory.mkdir();
			System.out.println(dir+"  is created");
		}
		
	}
}
