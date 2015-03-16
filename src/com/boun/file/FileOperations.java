package com.boun.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileOperations {

	public static void writeFile(String fileName,String content) throws IOException {
		
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
	
	
}
