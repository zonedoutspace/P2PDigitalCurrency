package com.boun.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.boun.server.CreateGenesis;
import com.boun.server.Properties;
import com.boun.signature.Generator;


public class FileOperations {

	public static void setup() throws Exception{
		
		
		createDirectories();
		Generator.createMyKeys();
		
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
		
		createADirectory(CreateGenesis.GENESIS_DIRECTORY)
		;
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
